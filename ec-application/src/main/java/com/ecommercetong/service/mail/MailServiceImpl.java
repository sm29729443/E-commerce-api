package com.ecommercetong.service.mail;

import com.ecommercetong.enums.BusinessScenarioEnum;
import com.ecommercetong.template.MailTemplate;
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
    private Map<BusinessScenarioEnum, MailTemplate> mailTemplateMap;

    @Override
    public void sendMailWithTemplate(String sendTo,
                                     BusinessScenarioEnum mailTemplateEnum,
                                     Map<String, Object> scenarioEnum) {
        MailTemplate mailTemplate = mailTemplateMap.get(mailTemplateEnum);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject(mailTemplate.getSubject());
        simpleMailMessage.setText(mailTemplate.buildContent(scenarioEnum));
        javaMailSender.send(simpleMailMessage);

    }

    @Override
    public void sendMailWithTemplate(String sendTo, BusinessScenarioEnum scenarioEnum) {
        sendMailWithTemplate(sendTo, scenarioEnum, null);
    }
}
