package com.ecommercetong.config;

import com.ecommercetong.enums.MailTemplateName;
import com.ecommercetong.template.LoginSuccessTemplate;
import com.ecommercetong.template.MailTemplate;
import com.ecommercetong.template.VerificationCodeMailTemplate;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MailTemplateConfig {

  @Bean(name = "mailTemplateMap")
  public Map<MailTemplateName, MailTemplate> setMailTemplateMap() {
    HashMap<MailTemplateName, MailTemplate> mailTemplateMap = new HashMap<>();
    mailTemplateMap.put(
        MailTemplateName.VerificationCodeMailTemplate, new VerificationCodeMailTemplate());
    mailTemplateMap.put(MailTemplateName.LoginSuccessMailTemplate, new LoginSuccessTemplate());
    return mailTemplateMap;
  }
}
