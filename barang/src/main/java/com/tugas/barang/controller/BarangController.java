package com.tugas.barang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.tugas.barang.payload.req.BarangPayloadReq;
import com.tugas.barang.payload.req.PenjualanPayloadReq;
import com.tugas.barang.payload.res.BarangPayloadRes;
import com.tugas.barang.payload.res.PenjualanPayloadRes;
import com.tugas.barang.service.BarangService;
import com.tugas.barang.utility.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/barang")
public class BarangController {
    @Autowired
    BarangService barangService;

    @GetMapping("/getBarang")
    public ResponseEntity getBarang(@RequestBody BarangPayloadReq payload) {
        System.out.println("ID Barang yang diterima: " + payload.getIdBarangReq());
        try {
            BarangPayloadRes results = barangService.getDataBarang(payload);
            System.out.println("Barang : " + results);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("tidak ditemukan")) {
                return new Message().error(e.getMessage(), 404);
            }
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    @GetMapping("/getAllBarang")
    public ResponseEntity getAllBarang() {
        try {
            List<BarangPayloadRes> results = barangService.getAllDataBarang();
            System.out.println("Barang : " + results);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    @GetMapping("/cekStock")
    public ResponseEntity cekStock(@RequestParam("idBarang") int idBarang) {
        System.out.println("Cek sisa stok untuk ID Barang: " + idBarang);
        try {

            PenjualanPayloadReq payload = new PenjualanPayloadReq();
            payload.setIdPenjualanReq(idBarang);

            BarangPayloadRes data = barangService.cekStock(payload);

            Map<String, Object> results = new HashMap<>();
            results.put("idBarang", data.getIdBarangRes());
            results.put("namaBarang", data.getNamaBarangRes());
            results.put("hargaBarang", data.getHargaBarangRes());
            results.put("sisaStock", data.getStokBarangRes());

            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    @GetMapping("/cekStockByPenjualan")
    public ResponseEntity cekStockByPenjualan(@RequestParam("idPenjualan") int idPenjualan) {
        System.out.println("Mengecek sisa stok dari riwayat ID Penjualan: " + idPenjualan);
        try {
            BarangPayloadRes data = barangService.cekStockByIdPenjualan(idPenjualan);
            PenjualanPayloadRes penjualanData = barangService.cekJumlahBeli(idPenjualan);
            Map<String, Object> results = new HashMap<>();
            results.put("idPenjualan", idPenjualan);
            results.put("idBarang", data.getIdBarangRes());
            results.put("namaBarang", data.getNamaBarangRes());
            results.put("hargaBarang", data.getHargaBarangRes());
            results.put("sisaStockSekarang", data.getStokBarangRes());
            results.put("jumlahBeli", penjualanData.getJumlahBeliRes());
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new Message().error("Terjadi Error: " + e.getMessage(), 404);
        }
    }

    @GetMapping("/getAllPenjualan")
    public ResponseEntity getAllPenjualan() {
        try {
            List<PenjualanPayloadRes> results = barangService.getAllPenjualan();
            System.out.println("Penjualan : " + results);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

}
