package com.tugas.penjualan.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Message {
    
    // Respon Sukses Tanpa Data Tambahan
    public ResponseEntity success(String message, int status){
        Map<String,Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    // Respon Ditolak (Forbidden)
    public ResponseEntity accessDenied(){
        Map<String,Object> res = new HashMap<>();
        res.put("error", "access_denied");
        res.put("status", "Access is denied");
        return new ResponseEntity(res, HttpStatus.FORBIDDEN);
    }
    
    // Respon Error Server
    public ResponseEntity error(String message, int status){
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        return new ResponseEntity(res, HttpStatus.valueOf(status));
    }
    
    // Respon Sukses Bawa Data (Ini yang sering kita pakai untuk nampilin PayloadRes)
    public ResponseEntity success(String message, Object data, int status){
        Map<String,Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        res.put("data", data);
        return new ResponseEntity(res, HttpStatus.OK);
    }
    
    // Respon Konflik / Data Sudah Ada
    public ResponseEntity conflict(String message, int status){
        Map<String,Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        return new ResponseEntity(res, HttpStatus.CONFLICT);
    }

    // Respon Request Ditolak (Misal: Stok Habis / Data Input Salah)
    public ResponseEntity badReq(String message, int status){
        Map<String,Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
    }
}