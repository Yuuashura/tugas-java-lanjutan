package com.mini.project.service;

import com.mini.project.dto.LoginReq;
import com.mini.project.dto.OtpInfo;
import com.mini.project.dto.RegisterReq;
import com.mini.project.model.UserEntity;
import com.mini.project.repo.UserRepository;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JavaMailSender mailSender; // Kurir email otomatis dari Spring Boot

    // Memori sementara untuk menyimpan OTP (Email -> Data OTP)
    // Kita pakai ConcurrentHashMap agar aman kalau ada banyak user yang daftar
    // bersamaan
    private final Map<String, OtpInfo> otpStorage = new ConcurrentHashMap<>();

    // ==========================================
    // 1. SIHIR MINTA OTP (Cooldown 15 Menit)
    // ==========================================
    public void generateAndSendOtp(String email) throws Exception {

        // Cek apakah email sudah jadi warga Asura
        if (userRepo.findByEmail(email).isPresent()) {
            throw new Exception("Email ini sudah terdaftar!");
        }

        // Cek Cooldown (Apakah user ini baru saja minta OTP?)
        if (otpStorage.containsKey(email)) {
            LocalDateTime waktuMintaSebelumnya = otpStorage.get(email).getRequestTime();
            LocalDateTime batasWaktu = waktuMintaSebelumnya.plusMinutes(15);

            if (LocalDateTime.now().isBefore(batasWaktu)) {
                long sisaMenit = Duration.between(LocalDateTime.now(), batasWaktu).toMinutes();
                throw new Exception("Sihir tertahan! Paduka harus menunggu " + (sisaMenit + 1) + " menit lagi.");
            }
        }

        // Buat 6 angka OTP acak
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);

        // Simpan ke memori
        otpStorage.put(email, new OtpInfo(otp, LocalDateTime.now()));

        System.out.println(otpStorage );

        // Terbangkan burung merpati (Kirim Email)
        String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; border: 1px solid #444; border-radius: 10px; overflow: hidden;'>"
                +
                "<div style='background-color: #1a1a1a; padding: 20px; text-align: center;'>" +
                "<h1 style='color: #ff4757; margin: 0;'>KERAJAAN ASURA</h1>" +
                "</div>" +
                "<div style='padding: 30px; background-color: #ffffff; color: #333;'>" +
                "<h2>Wahai Calon Warga Asura!</h2>" +
                "<p>Paduka telah terpilih untuk bergabung dalam kekuatan kegelapan yang agung. Gunakan kode rahasia di bawah ini untuk memverifikasi jati diri Paduka:</p>"
                +
                "<div style='background-color: #f4f4f4; padding: 20px; text-align: center; border-radius: 8px; margin: 20px 0;'>"
                +
                "<span style='font-size: 32px; font-weight: bold; letter-spacing: 5px; color: #1e90ff;'>" + otp
                + "</span>" +
                "</div>" +
                "<p style='color: #777; font-size: 14px;'>*Sihir ini akan kadaluarsa dalam 15 menit. Jangan berikan kode ini kepada penyusup manapun!</p>"
                +
                "</div>" +
                "<div style='background-color: #f9f9f9; padding: 15px; text-align: center; font-size: 12px; color: #999;'>"
                +
                "© 2026 Kerajaan Asura - Microservice Auth Power" +
                "</div>" +
                "</div>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("Undangan Rahasia Kerajaan Asura 👑");
        message.setSubject("Kode Verifikasi Kerajaan Asura 👑");
        helper.setText(htmlContent, true);
        mailSender.send(message);
    }

    // ==========================================
    // 2. SIHIR REGISTER (Validasi OTP & Save)
    // ==========================================
    @Transactional
    public UserEntity registerUser(RegisterReq req) throws Exception {

        try {

            if (!otpStorage.containsKey(req.getEmail())) {
                throw new Exception("Tidak ada permintaan OTP untuk email ini!");
            }

            OtpInfo savedData = otpStorage.get(req.getEmail());

            // Cek apakah kode OTP sama
            if (!savedData.getOtpCode().equals(req.getOtp())) {
                throw new Exception("Kode OTP salah!");
            }

            // Cek apakah OTP sudah basi (Lebih dari 15 menit)
            if (LocalDateTime.now().isAfter(savedData.getRequestTime().plusMinutes(15))) {
                otpStorage.remove(req.getEmail());
                throw new Exception("Kode OTP sudah kadaluarsa! Silakan minta ulang.");
            }

            // Jika semua lolos, masukkan ke Database!
            UserEntity newUser = new UserEntity();
            newUser.setEmail(req.getEmail());
            newUser.setNamaLengkap(req.getNamaLengkap());
            newUser.setJenisKelamin(req.getJenisKelamin());
            newUser.setTanggalLahir(req.getTanggalLahir());
            newUser.setPassword(passwordEncoder.encode(req.getPassword()));
            UserEntity savedUser = userRepo.save(newUser);

            // Hapus OTP dari memori karena sudah sukses dipakai
            otpStorage.remove(req.getEmail());

            return savedUser;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // ==========================================
    // 3. SIHIR LOGIN
    // ==========================================

    // Di AuthService.java tambahkan:

    public Map<String, Object> loginUser(LoginReq req) throws Exception {
        UserEntity user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new Exception("Email tidak ditemukan!"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new Exception("Password salah!");
        }

        // Buat token paspor
        String token = jwtService.generateToken(user.getEmail());

        // Kembalikan data user + tokennya
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("token", token);
        return response;
    }
}