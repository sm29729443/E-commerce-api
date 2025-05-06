package com.ecommercetong.template;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginSuccessTemplate implements MailTemplate {
    @Override
    public String getSubject() {
        return "Login Success";
    }
    @Override
    public String buildContent(Map<String, Object> templateParams) {
        return "Login Success Content";
    }
}
