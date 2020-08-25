package com.smj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: Administrator
 * @Description: TODO
 * @Date: 2020/08/21 17:11
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication7002.class, args);
    }
}
