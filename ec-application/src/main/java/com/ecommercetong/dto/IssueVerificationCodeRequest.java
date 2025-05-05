package com.ecommercetong.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class IssueVerificationCodeRequest {
    @Email(message = "Email format is not valid")
    private String email;
}
