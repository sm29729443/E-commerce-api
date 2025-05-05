package com.ecommercetong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @PostMapping("/register")
    public ResponseEntity<?> register() {
        return ResponseEntity.ok().body("register success");
    }

    @PostMapping("/auth/issueVerificationCode")
    public ResponseEntity<?> issueVerificationCode(

    ) {
        return ResponseEntity.ok().body("register success");
    }
}
