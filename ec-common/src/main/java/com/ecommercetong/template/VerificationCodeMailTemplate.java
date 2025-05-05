package com.ecommercetong.template;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VerificationCodeMailTemplate implements MailTemplate {

    @Override
    public String getSubject() {
        return "Verification Code";
    }
    @Override
    public String buildContent(Map<String, Object> templateParams) {
        return "驗證碼信件內容";
    }

}
