package com.mini.project.utility;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Sihir untuk menangkap error validasi (@NotBlank, @Email, dll)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Ambil hanya pesan error pertamanya saja (biar simpel)
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        
        // Kembalikan menggunakan format pusaka Message Paduka!
        return new Message().badRequest(errorMessage, 400);
    }

    // Sihir untuk menangkap error umum lainnya
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        return new Message().internalServerError(ex.getMessage(), 500);
    }
}