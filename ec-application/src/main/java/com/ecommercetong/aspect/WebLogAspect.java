package com.ecommercetong.aspect;

import lombok.Data;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Data
@Component
public class WebLogAspect {

  @Before("execution(com.ecommercetong.controller)")
  public void logStart() {}
}
