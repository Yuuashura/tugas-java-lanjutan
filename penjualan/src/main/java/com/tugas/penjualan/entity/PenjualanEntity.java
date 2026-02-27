package com.tugas.penjualan.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "penjualan")
public class PenjualanEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_penjualan")
    private int idPenjualan;
    @Column(name = "nama_pembeli")
    private String namaPembeli;
    @Column(name = "id_barang")
    private int idBarang;
    @Column(name = "jumlah_beli")
    private int jumlahBeli;
    @Column(name = "total_harga")
    private int totalHarga;
    @CreationTimestamp
    @Column(name = "tanggal_penjualan", updatable = false)
    private LocalDateTime tanggalPenjualan; //TimeStamp

    public int getIdPenjualan() { return idPenjualan; }
    public void setIdPenjualan(int idPenjualan) { this.idPenjualan = idPenjualan; }
    public String getNamaPembeli() { return namaPembeli; }
    public void setNamaPembeli(String namaPembeli) { this.namaPembeli = namaPembeli; }
    public int getIdBarang() { return idBarang; }
    public void setIdBarang(int idBarang) { this.idBarang = idBarang; }
    public int getJumlahBeli() { return jumlahBeli; }
    public void setJumlahBeli(int jumlahBeli) { this.jumlahBeli = jumlahBeli; }
    public int getTotalHarga() { return totalHarga; }
    public void setTotalHarga(int totalHarga) { this.totalHarga = totalHarga; }
    public LocalDateTime getTanggalPenjualan() { return tanggalPenjualan; }
    public void setTanggalPenjualan(LocalDateTime tanggalPenjualan) { this.tanggalPenjualan = tanggalPenjualan; }
}