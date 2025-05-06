package com.ecommercetong.service.verification;

import com.ecommercetong.enums.MailTemplateName;
import com.ecommercetong.service.mail.MailService;
import com.ecommercetong.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
  @Autowired private StringRedisTemplate stringRedisTemplate;
  @Autowired private MailService mailService;

  @Override
  public void generateVerificationCode(String email) {
    String code = Utils.generateRandomCode(6);
    stringRedisTemplate.opsForValue().set(email, code);
    new Thread() {
      @Override
      public void run() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(email, code);
        mailService.sendMailWithTemplate(email, MailTemplateName.VerificationCodeMailTemplate, map);
      }
    }.start();
  }
}
