package com.xinan.user.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Component
@Data
@Configuration
public class Config {
    @Value("${zuul_address}")
    private  String zuul_address;
}
