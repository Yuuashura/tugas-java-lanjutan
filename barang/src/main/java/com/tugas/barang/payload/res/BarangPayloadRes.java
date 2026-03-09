package com.tugas.barang.payload.res;

public class BarangPayloadRes {
    
    private int idBarangRes;
    private String namaBarangRes;
    private int hargaBarangRes;
    private int stokBarangRes;
    private String status;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
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
