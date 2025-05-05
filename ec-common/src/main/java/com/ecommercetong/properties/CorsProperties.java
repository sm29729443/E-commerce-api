package com.ecommercetong.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cors")
@Data
public class CorsProperties {
    private String allowedOrigins;
}
