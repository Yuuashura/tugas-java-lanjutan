package com.tugas.penjualan.payload.res;

import java.time.LocalDateTime; // <--- Mantra untuk waktu saat ini

public class EmailPayloadRes {
    
    private String recipientEmail;
    private String statusPengiriman;
    private String waktuKirim;

    // --- Generate Getter dan Setter ---
    public String getRecipientEmail() { return recipientEmail; }
    public void setRecipientEmail(String recipientEmail) { this.recipientEmail = recipientEmail; }

    public String getStatusPengiriman() { return statusPengiriman; }
    public void setStatusPengiriman(String statusPengiriman) { this.statusPengiriman = statusPengiriman; }

    public String getWaktuKirim() { return waktuKirim; }
    public void setWaktuKirim(String waktuKirim) { this.waktuKirim = waktuKirim; }
}