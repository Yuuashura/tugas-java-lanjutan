package com.mini.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelamin;

    @Column(name = "tanggal_lahir", nullable = false)
    private String tanggalLahir;

    @Column(nullable = false)
    private String password; // Nanti wajib kita enkripsi!
}