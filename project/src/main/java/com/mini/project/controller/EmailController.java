package com.mini.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.project.dto.EmailSendReq;
import com.mini.project.service.EmailSendService;
import com.mini.project.utility.Message;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class EmailController {
    

    @Autowired
    EmailSendService emailSendService;

    @PostMapping("/sendDetailBarang")
    public ResponseEntity<?> kirimDetailBarang(@RequestBody EmailSendReq payloadReq) {
        try {
            Map<String, Object> res = emailSendService.sendDetailBarang(payloadReq.getEmail());

            return new ResponseEntity<>(res, HttpStatus.OK);
            
        } catch (Exception e) {
            return new Message().error("Error : " + e.getMessage(), 400);
        }
        
    }
    

}
