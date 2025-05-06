package com.ecommercetong.service.verification;

import com.ecommercetong.enums.MailTemplateName;
import com.ecommercetong.exception.CustomizedException;
import com.ecommercetong.service.mail.MailService;
import com.ecommercetong.utils.Utils;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MailService mailService;

    @Override
    public void generateVerificationCode(String mail) {
        // 10 分鐘內最多獲取 3 次
        String verificationCounterKey = "verificationCounter:" + mail;
        int limit = 3;
        Boolean counterKeyExists = stringRedisTemplate.hasKey(verificationCounterKey);
        if (counterKeyExists) {
            Long count = stringRedisTemplate.opsForValue().increment(verificationCounterKey);
            if (count > limit) {
                Long countTimeLeft = stringRedisTemplate.getExpire(verificationCounterKey, TimeUnit.MINUTES);
                throw new CustomizedException(HttpStatus.BAD_REQUEST,
                        "10 分內最多獲取 3 次驗證碼, 請" + countTimeLeft + "分後再試");
            }
        } else {
            stringRedisTemplate.opsForValue().set(verificationCounterKey, "1", 10, TimeUnit.MINUTES);
        }
        // 生成驗證碼
        String code = Utils.generateRandomCode(6);
        // 將 mail、驗證碼放到 redis、期限為 1 分鐘
        String verificationKey = "verificationCode:" + mail;
        Long expire = stringRedisTemplate.getExpire(verificationKey, TimeUnit.SECONDS);
        // 如果驗證碼還沒過期，不發送新的
        if (expire > 0) {
            throw new CustomizedException(HttpStatus.BAD_REQUEST, "請在" + expire + "秒後重新獲取");
        }
        stringRedisTemplate.opsForValue().set(verificationKey, code, 2, TimeUnit.MINUTES);

        // 將驗證碼發送給用戶
        new Thread() {
            @Override
            public void run() {
                HashMap<String, Object> templateParams = new HashMap<>();
                templateParams.put("verificationCode", code);
                mailService.sendMailWithTemplate(mail, MailTemplateName.VerificationCodeMailTemplate, templateParams);
            }
        }.start();
    }
}
