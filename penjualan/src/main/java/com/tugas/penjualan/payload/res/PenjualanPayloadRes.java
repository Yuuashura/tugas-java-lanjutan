package com.tugas.penjualan.payload.res;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PenjualanPayloadRes {
    
    @JsonProperty("idPenjualan")
    private int idPenjualanRes;
    
    @JsonProperty("idBarang")
    private int idBarangRes;
    
    @JsonProperty("jumlahBeli")
    private int jumlahBeliRes;

    @JsonProperty("sisaStockSekarang")
    private int sisaStockSekarangRes;

    @JsonProperty("namaBarang")
    private String namaBarangRes;

    @JsonProperty("hargaBarang")
    private int hargaBarangRes;

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

    public int getJumlahBeliRes() {
        return jumlahBeliRes;
    }

    public void setJumlahBeliRes(int jumlahBeliRes) {
        this.jumlahBeliRes = jumlahBeliRes;
    }

    public int getSisaStockSekarangRes() {
        return sisaStockSekarangRes;
    }

    public void setSisaStockSekarangRes(int sisaStockSekarangRes) {
        this.sisaStockSekarangRes = sisaStockSekarangRes;
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

    
}