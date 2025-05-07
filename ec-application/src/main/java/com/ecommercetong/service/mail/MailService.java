package com.ecommercetong.service.mail;

import com.ecommercetong.enums.BusinessScenarioEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
@Validated
public interface MailService {
    public void sendMailWithTemplate(@Email(message = "請輸入有效的 gmail") String sendTo,
                                     @NotNull(message = "請選擇發送的樣板") BusinessScenarioEnum scenarioEnum,
                                     Map<String, Object> templateParams);
    public void sendMailWithTemplate(String sendTo, BusinessScenarioEnum scenarioEnum);
}
