package com.tugas.barang.payload.res;

import com.tugas.barang.entity.BarangEntity;

public class PenjualanPayloadRes {
    private int idPenjualanRes;
    private String namaPembeliRes;
    private BarangEntity barangRes;
    private int jumlahBeliRes;
    private String tanggalPenjualanRes;
    public int getIdPenjualanRes() {
        return idPenjualanRes;
    }
    public void setIdPenjualanRes(int idPenjualanRes) {
        this.idPenjualanRes = idPenjualanRes;
    }
    public String getNamaPembeliRes() {
        return namaPembeliRes;
    }
    public void setNamaPembeliRes(String namaPembeliRes) {
        this.namaPembeliRes = namaPembeliRes;
    }
    public BarangEntity getBarangRes() {
        return barangRes;
    }
    public void setBarangRes(BarangEntity barangRes) {
        this.barangRes = barangRes;
    }
    public int getJumlahBeliRes() {
        return jumlahBeliRes;
    }
    public void setJumlahBeliRes(int jumlahBeliResRes) {
        this.jumlahBeliRes = jumlahBeliResRes;
    }
    public String getTanggalPenjualanRes() {
        return tanggalPenjualanRes;
    }
    public void setTanggalPenjualanRes(String tanggalPenjualanRes) {
        this.tanggalPenjualanRes = tanggalPenjualanRes;
    }
}
