package com.tugas.penjualan.controller;

import com.tugas.penjualan.payload.req.PenjualanPayloadReq;
import com.tugas.penjualan.payload.res.BarangPayloadRes;
import com.tugas.penjualan.payload.res.PenjualanPayloadRes;
import com.tugas.penjualan.service.PenjualanService;
import com.tugas.penjualan.utility.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;



@RestController
@RequestMapping("/penjualan")
public class PenjualanController {
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8000").build();

    @Autowired
    PenjualanService penjualanService;

    // Endpoint untuk mendapatkan stock barang berdasarkan idBarang
    @GetMapping("/getStockByBarang")
    public ResponseEntity<?> getStockByBarang(@RequestParam String idBarang) {
        System.out.println("Id Barang : " + idBarang);
        try {
            BarangPayloadRes res = webClient.get()
                    .uri("/barang/cekStock?idBarang={idBarang}", idBarang)
                    .retrieve()
                    .bodyToMono(BarangPayloadRes.class)
                    .block();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause().toString().contains("Connection refused")) {
                return new ResponseEntity<>("koneksi gagal", HttpStatus.SERVICE_UNAVAILABLE);
            }
            return new ResponseEntity<>("Gagal karena lain hal", HttpStatus.NOT_FOUND);
        }
    }



    // untuk melihat semua transaksi (masih belum ada paginasi ya a)
    @GetMapping("/getPenjualan")
    public ResponseEntity<?> getAllPenjualan() {
        try{
            List<PenjualanPayloadRes> res = penjualanService.getAllPenjualan();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Gagal karena lain hal", HttpStatus.NOT_FOUND);
        }
    }

   @PostMapping("/tambahPenjualan")
    public ResponseEntity<?> tambahPenjualan(@RequestBody PenjualanPayloadReq payload) {
        try {
            PenjualanPayloadRes hasil = penjualanService.addPenjualan(payload);            
            return new Message().success("Transaksi penjualan berhasil dicatat!", hasil, 200);
        } catch (Exception e) {
            return new Message().badReq(e.getMessage(), 400);
        }
    }
    

}
