package com.tugas.email.payload.req;

import lombok.Data;

@Data // Anotasi Lombok untuk membuat Getter & Setter otomatis
public class EmailRequestDto {
    private String to;       // Alamat email tujuan
    private String subject;  // Judul email
    private String body;     // Isi email
}