package com.ecommercetong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.ecommercetong.properties.CorsProperties;

import java.util.List;

@EnableWebSecurity(debug = false)
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class SecurityConfig {

  @Autowired private CorsProperties corsProperties;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http
            .cors(cors ->
                    cors.configurationSource(corsConfigurationSource()))
            // 採 jwt 認證不用防範 csrf，jwt 打算放 local storage
            .csrf(AbstractHttpConfigurer::disable)
            // 狀態是靠 JWT，應該不會有場景是 security 需要用到 session 的
            .sessionManagement(
                    session ->
                            session.sessionCreationPolicy(
                                    SessionCreationPolicy.STATELESS))
            .build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration apiConfiguration = new CorsConfiguration();
    apiConfiguration.setAllowedOriginPatterns(List.of(corsProperties.getAllowedOrigins()));
    apiConfiguration.setAllowCredentials(true);
    apiConfiguration.addAllowedHeader(CorsConfiguration.ALL);
    apiConfiguration.addAllowedMethod(CorsConfiguration.ALL);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/api/**", apiConfiguration);
    source.registerCorsConfiguration("/web/api/**", apiConfiguration);
    return source;
  }
}
