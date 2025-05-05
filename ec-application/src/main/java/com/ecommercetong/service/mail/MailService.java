package com.ecommercetong.service.mail;

import com.ecommercetong.enums.MailTemplateName;
import com.ecommercetong.template.MailTemplate;

import java.util.Map;

public interface MailService {
    public void sendMailWithTemplate(String sendTo,
                                     MailTemplateName mailTemplateName,
                                     Map<String, Object> templateParams);
    public void sendMailWithTemplate(String sendTo, MailTemplateName mailTemplateName);
}
