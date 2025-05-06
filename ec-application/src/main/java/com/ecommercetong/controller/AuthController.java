package com.ecommercetong.controller;

import com.ecommercetong.request.VerificationCodeRequest;
import com.ecommercetong.response.ApiResponse;
import com.ecommercetong.service.mail.MailService;
import com.ecommercetong.service.verification.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

  @Autowired private VerificationCodeService verificationCodeService;
  @PostMapping("/register")
  public ResponseEntity<?> register() {
    return ResponseEntity.ok().body("register success");
  }

  @PostMapping("/auth/verification-code")
  public ResponseEntity<ApiResponse> issueVerificationCode(@RequestBody VerificationCodeRequest verificationCodeRequest) {
    verificationCodeService.generateVerificationCode(verificationCodeRequest.getMail());
    return ResponseEntity.ok().body(
            ApiResponse.wrapperResponse(HttpStatus.OK,null,"issue verification code success"));
  }
}
