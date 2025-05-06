package com.ecommercetong.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VerificationCodeRequest {
    @NotNull(message = "Verification code is required")
    @Email(message = "Email format is not valid")
    @Pattern(message = "目前只支持 Gmail", regexp = ".*@gmail\\.com")
    private String mail;

}
