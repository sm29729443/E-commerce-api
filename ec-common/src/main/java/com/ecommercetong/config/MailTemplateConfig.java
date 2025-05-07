package com.ecommercetong.config;

import com.ecommercetong.enums.BusinessScenarioEnum;
import com.ecommercetong.template.LoginSuccessTemplate;
import com.ecommercetong.template.MailTemplate;
import com.ecommercetong.template.VerificationCodeMailTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MailTemplateConfig {

  @Bean(name = "mailTemplateMap")
  public Map<BusinessScenarioEnum, MailTemplate> setMailTemplateMap() {
    HashMap<BusinessScenarioEnum, MailTemplate> mailTemplateMap = new HashMap<>();
    mailTemplateMap.put(
        BusinessScenarioEnum.VERIFY_CODE, new VerificationCodeMailTemplate());
    mailTemplateMap.put(BusinessScenarioEnum.LOGIN_SUCCESS, new LoginSuccessTemplate());
    return mailTemplateMap;
  }
}
