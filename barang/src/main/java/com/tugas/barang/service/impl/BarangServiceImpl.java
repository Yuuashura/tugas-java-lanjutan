package com.tugas.barang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas.barang.entity.BarangEntity;
import com.tugas.barang.entity.PenjualanEntity;
import com.tugas.barang.payload.req.BarangPayloadReq;
import com.tugas.barang.payload.req.PenjualanPayloadReq;
import com.tugas.barang.payload.res.BarangPayloadRes;
import com.tugas.barang.payload.res.PenjualanPayloadRes;
import com.tugas.barang.repository.BarangRepository;
import com.tugas.barang.repository.PenjualanRepository;
import com.tugas.barang.service.BarangService;

@Service
public class BarangServiceImpl implements BarangService {

    @Autowired
    private BarangRepository barangRepo;

    @Autowired
    private PenjualanRepository penjualanRepo;

    @Override
    public BarangPayloadRes getDataBarang(BarangPayloadReq payload) throws Exception {
        BarangPayloadRes res = new BarangPayloadRes();
        System.out.println("ID: " + payload.getIdBarangReq());

        try {
            BarangEntity ent = barangRepo.findById(payload.getIdBarangReq())
                    .orElseThrow(() -> new Exception("Data tidak ditemukan"));
            if (ent == null) {
                System.out.println("Data tidak ditemukan");
            } else {
                System.out.println("Data ditemukan: " + ent.getNamaBarang());
                res.setIdBarangRes(ent.getIdBarang());
                res.setNamaBarangRes(ent.getNamaBarang());
                res.setHargaBarangRes(ent.getHargaBarang());
                res.setStokBarangRes(ent.getStokBarang());
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return res;
    }

    @Override
    public List<BarangPayloadRes> getAllDataBarang() throws Exception {
        try {
            return barangRepo.findAll().stream().map(ent -> {

                BarangPayloadRes res = new BarangPayloadRes();
                res.setIdBarangRes(ent.getIdBarang());
                res.setNamaBarangRes(ent.getNamaBarang());
                res.setHargaBarangRes(ent.getHargaBarang());
                res.setStokBarangRes(ent.getStokBarang());
                return res;
            }).toList();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @Override
    public BarangPayloadRes cekStock(PenjualanPayloadReq payload) throws Exception {
        BarangPayloadRes res = new BarangPayloadRes();
        try {
            BarangEntity ent = barangRepo.findById(payload.getIdPenjualanReq()).orElseThrow(() -> new Exception("Pusaka Kerajaan tidak ditemukan!"));
            int totalLaku = penjualanRepo.getTotalTerjualByIdBarang(ent.getIdBarang());
            int sisaStokAsli = ent.getStokBarang() - totalLaku;
            res.setIdBarangRes(ent.getIdBarang());
            res.setNamaBarangRes(ent.getNamaBarang());
            res.setHargaBarangRes(ent.getHargaBarang());
            res.setStokBarangRes(sisaStokAsli);

        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return res;
    }

    @Override
    public BarangPayloadRes cekStockByIdPenjualan(int idPenjualan) throws Exception {
        BarangPayloadRes res = new BarangPayloadRes();
        try {
            PenjualanEntity penjualan = penjualanRepo.findById(idPenjualan)
                    .orElseThrow(() -> new Exception("Data Transaksi Penjualan tidak ditemukan!"));

            BarangEntity ent = penjualan.getBarang();
            if (ent == null) {
                throw new Exception("Barang tidak ditemukan pada transaksi ini!");
            }
            int totalLaku = penjualanRepo.getTotalTerjualByIdBarang(ent.getIdBarang());
            int sisaStokAsli = ent.getStokBarang() - totalLaku;
            res.setIdBarangRes(ent.getIdBarang());
            res.setNamaBarangRes(ent.getNamaBarang());
            res.setHargaBarangRes(ent.getHargaBarang());
            res.setStokBarangRes(sisaStokAsli);

        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return res;
    }

    public PenjualanPayloadRes cekJumlahBeli(int idPenjualan) throws Exception {
        PenjualanPayloadRes res = new PenjualanPayloadRes();
        try {
            PenjualanEntity penjualan = penjualanRepo.findById(idPenjualan)
                    .orElseThrow(() -> new Exception("Data Transaksi Penjualan tidak ditemukan!"));
            res.setIdPenjualanRes(penjualan.getIdPenjualan());
            res.setNamaPembeliRes(penjualan.getNamaPembeli());
            res.setBarangRes(penjualan.getBarang());
            res.setJumlahBeliRes(penjualan.getJumlahBeli());
            res.setTanggalPenjualanRes(penjualan.getTanggalPenjualan());

        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return res;
    }

    @Override
    public List<PenjualanPayloadRes> getAllPenjualan() throws Exception {
        try {
            return penjualanRepo.findAll().stream().map(ent -> {
                PenjualanPayloadRes res = new PenjualanPayloadRes();
                res.setIdPenjualanRes(ent.getIdPenjualan());
                res.setNamaPembeliRes(ent.getNamaPembeli());
                res.setBarangRes(ent.getBarang());
                res.setJumlahBeliRes(ent.getJumlahBeli());
                res.setTanggalPenjualanRes(ent.getTanggalPenjualan());
                return res;
            }).toList();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}