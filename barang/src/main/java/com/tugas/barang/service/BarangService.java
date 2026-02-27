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

    public List<BarangPayloadRes> getAllDataBarang(int page) throws Exception;

    public BarangPayloadRes cekStock(PenjualanPayloadReq payload) throws Exception;

    public BarangPayloadRes updateStock(PenjualanPayloadReq payload) throws Exception;

    public BarangPayloadRes deleteBarang(BarangPayloadReq payload) throws Exception;
}
