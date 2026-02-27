package com.tugas.barang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tugas.barang.entity.BarangEntity;
import com.tugas.barang.payload.req.BarangPayloadReq;
import com.tugas.barang.payload.req.PenjualanPayloadReq;
import com.tugas.barang.payload.res.BarangPayloadRes;
import com.tugas.barang.payload.res.PenjualanPayloadRes;
import com.tugas.barang.repository.BarangRepository;
import com.tugas.barang.service.BarangService;
 
@Service
public class BarangServiceImpl implements BarangService {

    @Autowired
    private BarangRepository barangRepo;

 

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
                res.setStatus("OK");
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
                res.setStatus("OK");
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
        try {
            BarangPayloadRes res = new BarangPayloadRes();
            BarangEntity ent = barangRepo.findById(payload.getIdPenjualanReq())
                    .orElseThrow(() -> new Exception("Data tidak ditemukan"));
                    res.setIdBarangRes(ent.getIdBarang());
                    res.setNamaBarangRes(ent.getNamaBarang());
                    res.setHargaBarangRes(ent.getHargaBarang());
                    res.setStokBarangRes(ent.getStokBarang());
                    res.setStatus("OK");
            return res;
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }


    @Override
    public BarangPayloadRes updateStock(PenjualanPayloadReq payload) throws Exception {
        try {
            System.out.println("id dari service : " + payload.getIdBarangReq());
            System.out.println("JumlahBeli dari service : " + payload.getJumlahBeliReq());
            BarangEntity data = barangRepo.findById(payload.getIdBarangReq()).orElseThrow(()-> new Exception("Data Tidak Ditemukan"));
            if (data == null) {
                throw new Exception("Data Tidak Ditemukan");
            }
            int stockSekarang = data.getStokBarang();
            System.out.println("Service Stock Sekarang : " + stockSekarang);
            data.setStokBarang(stockSekarang - payload.getJumlahBeliReq());
            barangRepo.save(data); 
            System.out.println("Service Setelah Update : " + data.getStokBarang());
            
            BarangPayloadRes res = new BarangPayloadRes();
            res.setHargaBarangRes(data.getHargaBarang());
            res.setIdBarangRes(data.getIdBarang());
            res.setNamaBarangRes(data.getNamaBarang());
            res.setStokBarangRes(data.getStokBarang());
            return res;

        } catch (Exception e) {
            throw new Exception("Error NtahKenapa" + e.getMessage());
        }
    }

    @Override
    public BarangPayloadRes deleteBarang(BarangPayloadReq payload) throws Exception{
        try{
            System.out.println(payload.getIdBarangReq());
            BarangEntity data = barangRepo.findById(payload.getIdBarangReq()).orElse(null);
            System.out.println(data);
            BarangPayloadRes res = new BarangPayloadRes();
            res.setStatus("OK");
            res.setIdBarangRes(data.getIdBarang());
            res.setHargaBarangRes(data.getHargaBarang());
            res.setNamaBarangRes(data.getNamaBarang());
            res.setStokBarangRes(data.getStokBarang());
            barangRepo.deleteById(payload.getIdBarangReq());
            return res;
        }catch(Exception e){
            throw new Exception("Error " + e.getMessage());
        }
    }
}