package com.ecommercetong.enums;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BusinessScenarioEnum {
  VERIFY_CODE("verify-code"), // 發送驗證碼通知信
  LOGIN_SUCCESS("login-success"); // 登入成功通知信

  private String scenario;
  // 將業務場景映射成 Enum
  public static BusinessScenarioEnum decode(String scenario) {
    for (BusinessScenarioEnum businessScenarioEnum : BusinessScenarioEnum.values()) {
      if (businessScenarioEnum.scenario.equals(scenario)) {
        return businessScenarioEnum;
      }
    }
    throw new BusinessScenarioNotFoundException();
  }
}
