package com.ecommercetong.template;

import java.util.Map;

public interface MailTemplate {
    public String getSubject();
    public String buildContent(Map<String, Object> templateParams);
}
