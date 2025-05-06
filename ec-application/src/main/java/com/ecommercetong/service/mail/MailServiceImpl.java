package com.ecommercetong.service.mail;

import com.ecommercetong.enums.MailTemplateEnum;
import com.ecommercetong.template.MailTemplate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier("mailTemplateMap")
    private Map<MailTemplateEnum, MailTemplate> mailTemplateMap;

    @Override
    public void sendMailWithTemplate(String sendTo,
                                     MailTemplateEnum mailTemplateEnum,
                                     Map<String, Object> templateParams) {
        MailTemplate mailTemplate = mailTemplateMap.get(mailTemplateEnum);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject(mailTemplate.getSubject());
        simpleMailMessage.setText(mailTemplate.buildContent(templateParams));
        javaMailSender.send(simpleMailMessage);

    }

    @Override
    public void sendMailWithTemplate(String sendTo, MailTemplateEnum mailTemplateEnum) {
        sendMailWithTemplate(sendTo, mailTemplateEnum, null);
    }
}
