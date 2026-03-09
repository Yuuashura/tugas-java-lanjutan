package com.mini.project.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mini.project.dto.EmailSendReq;

import jakarta.mail.internet.MimeMessage;

@Component
@Service
public class EmailSendService {
    @Autowired
    private WebClient webClient;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ClientService clientService;

    public Map<String, Object> sendDetailBarang(String email) throws Exception {

        try {

            Map<String, Object> dataBarang = clientService.getMapData("/barang/allBarang", " ");
            List<Map<String, Object>> listBarang = (List<Map<String, Object>>) dataBarang.get("data");
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<h1>Daftar Produk Asura Store</h1>");
            htmlBuilder.append("<table border='1' style='border-collapse: collapse; width: 100%;'>");
            htmlBuilder.append("<tr style='background-color: #34495e; color: white;'>").append("<th>Nama Barang</th>")
                    .append("<th>Harga</th>")
                    .append("<th>Stok</th>")
                    .append("<th>Status</th>")
                    .append("</tr>");
            for (Map<String, Object> barang : listBarang) {
                String statusColor = "Tersedia".equals(barang.get("status")) ? "#27ae60" : "#c0392b";

                htmlBuilder.append("<tr>")
                        .append("<td style='padding: 8px; border: 1px solid #bdc3c7;'>")
                        .append(barang.get("namaBarangRes")).append("</td>")
                        .append("<td style='padding: 8px; border: 1px solid #bdc3c7;'>Rp ")
                        .append(barang.get("hargaBarangRes")).append("</td>")
                        .append("<td style='padding: 8px; border: 1px solid #bdc3c7;'>")
                        .append(barang.get("stokBarangRes")).append("</td>")
                        .append("<td style='padding: 8px; border: 1px solid #bdc3c7; color: ").append(statusColor)
                        .append("; font-weight: bold;'>")
                        .append(barang.get("status")).append("</td>")
                        .append("</tr>");
            }

            htmlBuilder.append("</table>");
            htmlBuilder.append("<p>Data Dari Toko Yuu Ashura.</p>");

            String htmlContent = htmlBuilder.toString();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Send data barang via email");
            helper.setText(htmlContent, true);

            mailSender.send(message);

            Map<String, Object> res = new HashMap<>();
            res.put("Status", "Success");
            res.put("Author", "Yuuashura");
            res.put("time", LocalDateTime.now());
            res.put("message", "Email berhasil dikirim ke : " + email);
            return res;
        } catch (Exception e) {
            throw new Exception("Error : " + e.getMessage());
        }
    }

    // @Scheduled(cron = "0 0 6 * * ?")
    @Scheduled(fixedRate = 5000)
    public void pesanTerjadwal() {
        try {
            String email = "tzyudistiraa@gmail.com";
            String baseUri = "http://localhost:8000";
            Map<String, Object> dataBarang = webClient.get()
                    .uri(baseUri + "/barang/allBarang")
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List<Map<String, Object>> listBarang = (List<Map<String, Object>>) dataBarang.get("data");

            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<h1 style='color: #2c3e50;'>Laporan Stok Barang - Asura Store</h1>");
            htmlBuilder.append(
                    "<table border='1' style='border-collapse: collapse; width: 100%; font-family: Arial, sans-serif;'>");
            htmlBuilder.append("<tr style='background-color: #34495e; color: white;'>")
                    .append("<th style='padding: 10px;'>Nama Barang</th>")
                    .append("<th style='padding: 10px;'>Harga</th>")
                    .append("<th style='padding: 10px;'>Stok</th>")
                    .append("<th style='padding: 10px;'>Status</th>")
                    .append("</tr>");

            for (Map<String, Object> barang : listBarang) {
                String statusColor = "Tersedia".equals(barang.get("status")) ? "#27ae60" : "#c0392b";

                htmlBuilder.append("<tr>")
                        .append("<td style='padding: 8px; border: 1px solid #bdc3c7;'>")
                        .append(barang.get("namaBarangRes")).append("</td>")
                        .append("<td style='padding: 8px; border: 1px solid #bdc3c7;'>Rp ")
                        .append(barang.get("hargaBarangRes")).append("</td>")
                        .append("<td style='padding: 8px; border: 1px solid #bdc3c7;'>")
                        .append(barang.get("stokBarangRes")).append("</td>")
                        .append("<td style='padding: 8px; border: 1px solid #bdc3c7; color: ").append(statusColor)
                        .append("; font-weight: bold;'>")
                        .append(barang.get("status")).append("</td>")
                        .append("</tr>");
            }

            htmlBuilder.append("</table>");
            htmlBuilder.append(
                    "<p style='margin-top: 20px;'>Data dikirim otomatis oleh Sistem Penjadwalan Toko Yuu Ashura.</p>");

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Update Stock Ashura Store :  [" + LocalDateTime.now() + "]");
            helper.setText(htmlBuilder.toString(), true);

            mailSender.send(message);
            System.out.println("Pesan Berhasil dikirim ke : " + email);

        } catch (Exception e) {
            System.err.println("Error :  " + e.getMessage());
        }
    }
}
