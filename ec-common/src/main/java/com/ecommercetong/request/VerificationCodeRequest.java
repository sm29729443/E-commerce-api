package com.ecommercetong.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VerificationCodeRequest {
    @NotNull(message = "請輸入 mail")
    @Email(message = "請輸入有效的 mail 格式，譬如:abc@xxx.com")
    @Pattern(message = "目前只支持 Gmail", regexp = ".*@gmail\\.com")
    private String mail;

}
