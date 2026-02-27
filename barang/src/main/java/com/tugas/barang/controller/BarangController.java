package com.tugas.barang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.tugas.barang.payload.req.BarangPayloadReq;
import com.tugas.barang.payload.req.PenjualanPayloadReq;
import com.tugas.barang.payload.res.BarangPayloadRes;
import com.tugas.barang.service.BarangService;
import com.tugas.barang.utility.Message;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/barang")
public class BarangController {
    @Autowired
    BarangService barangService;

    // Mapping get barang via id bodyrequesy
    @GetMapping("/getBarang")
    public ResponseEntity<?> getBarang(@RequestBody BarangPayloadReq payload) {
        System.out.println("ID Barang yang diterima: " + payload.getIdBarangReq());
        try {
            BarangPayloadRes results = barangService.getDataBarang(payload);
            System.out.println("Barang : " + results);
            return new Message().success("Data Berhasil Di Ambil!", results, 200);
        } catch (Exception e) {
            if (e.getMessage().contains("tidak ditemukan")) {
                return new Message().error(e.getMessage(), 404);
            }
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    // Untuk take semua barang, nanti bakal ditambahkan pageination
    @GetMapping("/getAllBarang")
    public ResponseEntity<?> getAllBarang(
        @RequestParam(defaultValue = "1") int page
    ) {
        try {
            List<BarangPayloadRes> results = barangService.getAllDataBarang(page);
            return new Message().succesPage("Data Berhasil Di Ambil!", results, 200, page);
        } catch (Exception e) {
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    // Cek Stock by Id Barang
    @GetMapping("/cekStock")
    public ResponseEntity<?> cekStock(@RequestParam("idBarang") int idBarang, HttpServletRequest request) {
        System.out.println("Cek sisa stok untuk ID Barang: " + idBarang);
        try {
            PenjualanPayloadReq payload = new PenjualanPayloadReq();
            payload.setIdPenjualanReq(idBarang);
            BarangPayloadRes data = barangService.cekStock(payload);
            String ipAddress = request.getRemoteAddr();

            Map<String, Object> results = new HashMap<>();
            results.put("idBarang", data.getIdBarangRes());
            results.put("namaBarang", data.getNamaBarangRes());
            results.put("hargaBarang", data.getHargaBarangRes());
            results.put("sisaStock", data.getStokBarangRes());
            results.put("status", data.getStatus());
            results.put("ipAddress", ipAddress);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    // Update Stock
    @PutMapping("/updateStock")
    public ResponseEntity<?> updateStock(@RequestBody PenjualanPayloadReq payload) {
        try {
            BarangPayloadRes results = barangService.updateStock(payload);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

    @DeleteMapping("/deleteBarang")
    public ResponseEntity<?> deleteBarang(@RequestBody BarangPayloadReq payload) {
        try {
            BarangPayloadRes res = barangService.deleteBarang(payload);
            return new Message().success("Data Berhasil Dihapus !", res, 200);
        } catch (Exception e) {
            return new Message().error("Terjadi Error: " + e.getMessage(), 500);
        }
    }

}
