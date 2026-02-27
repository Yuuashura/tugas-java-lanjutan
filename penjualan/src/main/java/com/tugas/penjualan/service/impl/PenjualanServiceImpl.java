package com.tugas.penjualan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.tugas.penjualan.entity.PenjualanEntity;
import com.tugas.penjualan.payload.req.PenjualanPayloadReq;
import com.tugas.penjualan.payload.res.PenjualanPayloadRes;
import com.tugas.penjualan.repository.PenjualanRepository;
import com.tugas.penjualan.service.ClientService;
import com.tugas.penjualan.service.PenjualanService;

@Service
public class PenjualanServiceImpl implements PenjualanService {
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8000").build();

    @Autowired
    private PenjualanRepository penjualanRepository;
    @Autowired
    private ClientService clientService;

    @Override
    @Transactional
    public List<PenjualanPayloadRes> getAllPenjualan() throws Exception {
        try {
            return penjualanRepository.findAll().stream().map(ent -> {
                PenjualanPayloadRes res = new PenjualanPayloadRes();
                res.setIdPenjualanRes(ent.getIdPenjualan());
                res.setNamaPembeliRes(ent.getNamaPembeli());
                res.setIdBarangRes(ent.getIdBarang());
                res.setJumlahBeliRes(ent.getJumlahBeli());
                res.setTotalHargaRes(ent.getTotalHarga());
                res.setTanggalPenjualanRes(ent.getTanggalPenjualan().toString());
                return res;
            }).toList();
        } catch (Exception e) {
            throw new Exception("Gagal mengambil penjualan: " + e.getMessage());
        }
    }

    public PenjualanPayloadRes addPenjualan(PenjualanPayloadReq penjualanPayloadReq) throws Exception {
        try {

            System.out.println("ID Barang yang dibeli: " + penjualanPayloadReq.getIdBarangReq());

            Map<String, Object> dataUpdate = new HashMap<>();
            dataUpdate.put("idBarang", penjualanPayloadReq.getIdBarangReq());
            dataUpdate.put("jumlahBeli", penjualanPayloadReq.getJumlahBeliReq());
            Map<String, Object> dataBarang = clientService.getMapData("/barang/cekStock?idBarang={idBarang}", penjualanPayloadReq.getIdBarangReq());

            if(dataBarang == null){
                throw new Exception("Data barang tidak ditemukan");
            }
            int stokBarang = (int) dataBarang.get("sisaStock");
            int hargaBarang = (int) dataBarang.get("hargaBarang");

            if (stokBarang < penjualanPayloadReq.getJumlahBeliReq()) {
                throw new Exception("Stok barang tidak cukup");
            }

            PenjualanEntity ent = new PenjualanEntity();
            ent.setIdBarang(penjualanPayloadReq.getIdBarangReq());
            ent.setNamaPembeli(penjualanPayloadReq.getNamaPembeliReq());
            ent.setJumlahBeli(penjualanPayloadReq.getJumlahBeliReq());
            ent.setTotalHarga(penjualanPayloadReq.getJumlahBeliReq() * hargaBarang);
            penjualanRepository.save(ent);

            Map updateStockBarang = webClient.put()
            .uri("/barang/updateStock")
            .bodyValue(dataUpdate)
            .retrieve()
            .bodyToMono(Map.class)
            .block();

            PenjualanPayloadRes res = new PenjualanPayloadRes();
            res.setIdPenjualanRes(ent.getIdPenjualan());
            res.setNamaPembeliRes(ent.getNamaPembeli());
            res.setIdBarangRes(ent.getIdBarang());
            res.setJumlahBeliRes(ent.getJumlahBeli());
            res.setTotalHargaRes(ent.getTotalHarga());
            return res;
        } catch (Exception e) {
            throw new Exception("Gagal menambahkan penjualan: " + e.getMessage());
        }
    }


}
