package com.tugas.barang.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Message {
    public ResponseEntity<?> success(String message, int status) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<?> accessDenied() {
        Map<String, Object> res = new HashMap<>();
        res.put("error", "access_denied");
        res.put("status", "Access is denied");
        return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<?> error(String message, int status) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        return new ResponseEntity<>(res, HttpStatus.valueOf(status));

    }

    public ResponseEntity<?> succesPage(String message, Object data, int status, int page) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);
        res.put("page", page);
        res.put("status", status);
        res.put("data", data);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<?> success(String message, Object data, int status) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        res.put("data", data);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<?> conflict(String message, int status) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> badReq(String message, int status) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
