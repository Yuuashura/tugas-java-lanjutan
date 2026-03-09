package com.mini.project.utility;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class Message {

    // ==========================================
    // 🟢 KELUARGA SUCCESS (Dengan Data)
    // ==========================================

    public ResponseEntity<?> success(String message, Object data, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        response.put("status", status);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<?> created(String message, Object data, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        response.put("status", status);
        return ResponseEntity.status(status).body(response);
    }

    // ERROR

    public ResponseEntity<?> error(String message, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<?> badRequest(String message, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<?> unauthorized(String message, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<?> forbidden(String message, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<?> notFound(String message, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<?> internalServerError(String message, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        return ResponseEntity.status(status).body(response);
    }
}