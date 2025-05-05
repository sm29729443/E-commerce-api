package com.ecommercetong.template;

import java.util.Map;

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
