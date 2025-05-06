package com.ecommercetong.config;

import com.ecommercetong.enums.MailTemplateEnum;
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
  public Map<MailTemplateEnum, MailTemplate> setMailTemplateMap() {
    HashMap<MailTemplateEnum, MailTemplate> mailTemplateMap = new HashMap<>();
    mailTemplateMap.put(
            MailTemplateEnum.VerificationCodeMailTemplate, new VerificationCodeMailTemplate());
    mailTemplateMap.put(MailTemplateEnum.LoginSuccessMailTemplate, new LoginSuccessTemplate());
    return mailTemplateMap;
  }
}
