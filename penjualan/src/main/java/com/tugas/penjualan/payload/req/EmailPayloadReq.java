package com.tugas.penjualan.payload.req;

public class EmailPayloadReq {
    private String senderEmail;
    private String senderPassword; // Ingat, ini harus App Password (Sandikan Aplikasi) jika pakai Gmail!
    
    // Tambahan atribut penting:
    private String recipientEmail;
    private String subject;
    private String body;

    // --- Generate Getter dan Setter untuk semuanya di bawah ini ---
    
    public String getSenderEmail() { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }
    
    public String getSenderPassword() { return senderPassword; }
    public void setSenderPassword(String senderPassword) { this.senderPassword = senderPassword; }
    
    public String getRecipientEmail() { return recipientEmail; }
    public void setRecipientEmail(String recipientEmail) { this.recipientEmail = recipientEmail; }
    
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}