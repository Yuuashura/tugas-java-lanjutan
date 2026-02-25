package com.tugas.barang.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "penjualan")
public class PenjualanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPenjualan;
    private String namaPembeli;

    @ManyToOne
    @JoinColumn(name = "id_barang", nullable = false)
    private BarangEntity barang;
    private int jumlahBeli;
    private int totalHarga;
    private String tanggalPenjualan;
    public int getIdPenjualan() {
        return idPenjualan;
    }
    public void setIdPenjualan(int idPenjualan) {
        this.idPenjualan = idPenjualan;
    }
    public String getNamaPembeli() {
        return namaPembeli;
    }
    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }
    public BarangEntity getBarang() {
        return barang;
    }
    public void setBarang(BarangEntity barang) {
        this.barang = barang;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }
    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }
    public int getTotalHarga() {
        return totalHarga;
    }
    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
    public String getTanggalPenjualan() {
        return tanggalPenjualan;
    }
    public void setTanggalPenjualan(String tanggalPenjualan) {
        this.tanggalPenjualan = tanggalPenjualan;
    }
}