package com.tugas.penjualan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.tugas.penjualan.payload.req.PenjualanPayloadReq;
import com.tugas.penjualan.payload.res.PenjualanPayloadRes;

@Service
public interface PenjualanService {
    public List<PenjualanPayloadRes> getAllPenjualan() throws Exception;
    public PenjualanPayloadRes addPenjualan(PenjualanPayloadReq penjualanPayloadReq) throws Exception;
}
