package com.tugas.penjualan.payload.res;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PenjualanPayloadRes {
    private int idPenjualanRes;
    private int idBarangRes;
    private String namaPembeliRes;
    private int jumlahBeliRes;
    private int totalHargaRes;
    private String tanggalPenjualanRes;
    public int getIdPenjualanRes() {
        return idPenjualanRes;
    }
    public void setIdPenjualanRes(int idPenjualanRes) {
        this.idPenjualanRes = idPenjualanRes;
    }
    public int getIdBarangRes() {
        return idBarangRes;
    }
    public void setIdBarangRes(int idBarangRes) {
        this.idBarangRes = idBarangRes;
    }
    public String getNamaPembeliRes() {
        return namaPembeliRes;
    }
    public void setNamaPembeliRes(String namaPembeliRes) {
        this.namaPembeliRes = namaPembeliRes;
    }
    public int getJumlahBeliRes() {
        return jumlahBeliRes;
    }
    public void setJumlahBeliRes(int jumlahBeliRes) {
        this.jumlahBeliRes = jumlahBeliRes;
    }
    public int getTotalHargaRes() {
        return totalHargaRes;
    }
    public void setTotalHargaRes(int totalHargaRes) {
        this.totalHargaRes = totalHargaRes;
    }
    public String getTanggalPenjualanRes() {
        return tanggalPenjualanRes;
    }
    public void setTanggalPenjualanRes(String tanggalPenjualanRes) {
        this.tanggalPenjualanRes = tanggalPenjualanRes;
    }

}