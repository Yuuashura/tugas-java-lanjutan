package com.tugas.penjualan.payload.res;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BarangPayloadRes {
    @JsonProperty("idBarang")
    private int idBarangRes;

    @JsonProperty("namaBarang")
    private String namaBarangRes;

    @JsonProperty("hargaBarang")
    private int hargaBarangRes;

    @JsonProperty("sisaStock")
    private int stokBarangRes;

    public int getIdBarangRes() {
        return idBarangRes;
    }

    public void setIdBarangRes(int idBarangRes) {
        this.idBarangRes = idBarangRes;
    }

    public String getNamaBarangRes() {
        return namaBarangRes;
    }

    public void setNamaBarangRes(String namaBarangRes) {
        this.namaBarangRes = namaBarangRes;
    }

    public int getHargaBarangRes() {
        return hargaBarangRes;
    }

    public void setHargaBarangRes(int hargaBarangRes) {
        this.hargaBarangRes = hargaBarangRes;
    }

    public int getStokBarangRes() {
        return stokBarangRes;
    }

    public void setStokBarangRes(int stokBarangRes) {
        this.stokBarangRes = stokBarangRes;
    }
}
