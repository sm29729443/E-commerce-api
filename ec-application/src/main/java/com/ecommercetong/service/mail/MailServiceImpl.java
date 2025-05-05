package com.ecommercetong.service.mail;

import com.ecommercetong.enums.MailTemplateName;
import com.ecommercetong.template.MailTemplate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier("mailTemplateMap")
    private Map<MailTemplateName, MailTemplate> mailTemplateMap;

    @Override
    public void sendMailWithTemplate(@Email(message = "請輸入有效的 gmail") String sendTo,
                                     @NotNull(message = "請選擇發送的樣板") MailTemplateName mailTemplateName,
                                     Map<String, Object> templateParams) {
        MailTemplate mailTemplate = mailTemplateMap.get(mailTemplateName);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject(mailTemplate.getSubject());
        simpleMailMessage.setText(mailTemplate.buildContent(templateParams));
        javaMailSender.send(simpleMailMessage);

    }

    @Override
    public void sendMailWithTemplate(String sendTo, @NotNull(message = "請選擇發送的樣板") MailTemplateName mailTemplateName) {
        sendMailWithTemplate(sendTo, mailTemplateName, null);
    }
}
