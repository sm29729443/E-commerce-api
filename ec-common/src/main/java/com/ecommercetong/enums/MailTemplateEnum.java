package com.ecommercetong.enums;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MailTemplateEnum {
    VerificationCodeMailTemplate("verifyCode"),
    LoginSuccessMailTemplate("loginSuccess");

    private String scenario;

    public static MailTemplateEnum decode(String scenario) {
        for (MailTemplateEnum template : MailTemplateEnum.values()) {
            if (template.getScenario().equals(scenario)) {
                return template;
            }
        }
        throw new IllegalArgumentException("No enum constant for scenario: " + scenario);
    }
}
