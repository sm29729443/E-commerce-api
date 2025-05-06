package com.ecommercetong.service.mail;

import com.ecommercetong.enums.MailTemplateEnum;
import com.ecommercetong.template.MailTemplate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
@Validated
public interface MailService {
    public void sendMailWithTemplate(@Email(message = "請輸入有效的 gmail") String sendTo,
                                     @NotNull(message = "請選擇發送的樣板") MailTemplateEnum mailTemplate,
                                     Map<String, Object> templateParams);
    public void sendMailWithTemplate(String sendTo, MailTemplateEnum mailTemplate);
}
