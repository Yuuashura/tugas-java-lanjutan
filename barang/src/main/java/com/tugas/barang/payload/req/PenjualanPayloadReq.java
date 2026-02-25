package com.tugas.barang.payload.req;

import com.tugas.barang.entity.BarangEntity;


public class PenjualanPayloadReq {
    private int idPenjualanReq;
    private String namaPembeliReq;
    private int idBarangReq;
    private int jumlahBeliReq;
    private String tanggalPenjualanReq;
    public int getIdPenjualanReq() {
        return idPenjualanReq;
    }
    public void setIdPenjualanReq(int idPenjualanReq) {
        this.idPenjualanReq = idPenjualanReq;
    }
    public String getNamaPembeliReq() {
        return namaPembeliReq;
    }
    public void setNamaPembeliReq(String namaPembeliReq) {
        this.namaPembeliReq = namaPembeliReq;
    }

    public int getJumlahBeliReq() {
        return jumlahBeliReq;
    }
    public void setJumlahBeliReq(int jumlahBeliReqReq) {
        this.jumlahBeliReq = jumlahBeliReqReq;
    }
    public String getTanggalPenjualanReq() {
        return tanggalPenjualanReq;
    }
    public void setTanggalPenjualanReq(String tanggalPenjualanReq) {
        this.tanggalPenjualanReq = tanggalPenjualanReq;
    }
    public int getIdBarangReq() {
        return idBarangReq;
    }
    public void setIdBarangReq(int idBarangReq) {
        this.idBarangReq = idBarangReq;
    }
}
