package com.tugas.barang.payload.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BarangPayloadReq {
    @JsonProperty("id")
    private int idBarangReq;
    private String namaBarangReq;
    private int hargaBarangReq;
    private int stokBarangReq;

    public int getIdBarangReq() {
        return idBarangReq;
    }
    public void setIdBarangReq(int idBarangReq) {
        this.idBarangReq = idBarangReq;
    }
    public String getNamaBarangReq() {
        return namaBarangReq;
    }
    public void setNamaBarangReq(String namaBarangReq) {
        this.namaBarangReq = namaBarangReq;
    }
    public int getHargaBarangReq() {
        return hargaBarangReq;
    }
    public void setHargaBarangReq(int hargaBarangReq) {
        this.hargaBarangReq = hargaBarangReq;
    }
    public int getStokBarangReq() {
        return stokBarangReq;
    }
    public void setStokBarangReq(int stokBarangReq) {
        this.stokBarangReq = stokBarangReq;
    }
    
}