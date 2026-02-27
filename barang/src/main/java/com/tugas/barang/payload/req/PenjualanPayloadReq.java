package com.tugas.barang.payload.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tugas.barang.entity.BarangEntity;


public class PenjualanPayloadReq {
    @JsonProperty("idPenjualan")
    private int idPenjualanReq;
    @JsonProperty("namaPembeli")
    private String namaPembeliReq;
    @JsonProperty("idBarang")
    private int idBarangReq;
    @JsonProperty("jumlahBeli")
    private int jumlahBeliReq;
    @JsonProperty("tanggalPenjualan")
    private String tanggalPenjualanReq;
    @JsonProperty("totalHarga")
    private int totalHargaReq;

    public int getTotalHargaReq() {
        return totalHargaReq;
    }
    public void setTotalHargaReq(int totalHargaReq) {
        this.totalHargaReq = totalHargaReq;
    }
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
