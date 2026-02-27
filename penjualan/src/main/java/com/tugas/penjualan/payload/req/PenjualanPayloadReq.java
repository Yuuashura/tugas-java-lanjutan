package com.tugas.penjualan.payload.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PenjualanPayloadReq {
    @JsonProperty("idPenjualan")
    private int idPenjualanReq;
    @JsonProperty("idBarang")
    private int idBarangReq;
    @JsonProperty("namaPembeli")
    private String namaPembeliReq;
    @JsonProperty("jumlahBeli")
    private int jumlahBeliReq;
    public int getIdPenjualanReq() {
        return idPenjualanReq;
    }
    public void setIdPenjualanReq(int idPenjualanReq) {
        this.idPenjualanReq = idPenjualanReq;
    }
    public int getIdBarangReq() {
        return idBarangReq;
    }
    public void setIdBarangReq(int idBarangReq) {
        this.idBarangReq = idBarangReq;
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
    public void setJumlahBeliReq(int jumlahBeliReq) {
        this.jumlahBeliReq = jumlahBeliReq;
    }

}
