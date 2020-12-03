package com.smj.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {
    @Bean
    Logger.Level feignLogerLevel(){
        return Logger.Level.FULL;
    }
}
