package com.ecommercetong.enums;

import com.ecommercetong.exception.BusinessScenarioException;
import lombok.*;
import org.springframework.http.HttpStatus;

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
    throw new BusinessScenarioException(HttpStatus.INTERNAL_SERVER_ERROR,
            "scenario enum not found");
  }
}
