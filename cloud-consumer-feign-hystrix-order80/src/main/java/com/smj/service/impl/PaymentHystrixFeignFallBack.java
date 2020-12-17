package com.smj.service.impl;

import com.smj.service.PaymentHystrixFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentHystrixFeignFallBack implements PaymentHystrixFeignService {
    @Override
    public String paymentOk(Integer id) {
        log.info("网络繁忙请稍后再试，接口[paymentOk]触发降级，param->{}",id);
        return "服务繁忙";
    }

    @Override
    public String psymentTimeout(Integer id) {
        log.info("网络繁忙请稍后再试，接口[psymentTimeout]触发降级，param->{}",id);
        return "服务繁忙";
    }
}
