package com.tugas.barang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tugas.barang.payload.req.BarangPayloadReq;
import com.tugas.barang.payload.req.PenjualanPayloadReq;
import com.tugas.barang.payload.res.BarangPayloadRes;
import com.tugas.barang.payload.res.PenjualanPayloadRes;

@Service
public interface BarangService {
    public BarangPayloadRes getDataBarang(BarangPayloadReq payload) throws Exception;

    public List<BarangPayloadRes> getAllDataBarang() throws Exception;

    public BarangPayloadRes cekStock(PenjualanPayloadReq payload) throws Exception;

    public BarangPayloadRes cekStockByIdPenjualan(int idPenjualan) throws Exception;

    public PenjualanPayloadRes cekJumlahBeli(int idPenjualan) throws Exception;

    public List<PenjualanPayloadRes> getAllPenjualan() throws Exception;
}
