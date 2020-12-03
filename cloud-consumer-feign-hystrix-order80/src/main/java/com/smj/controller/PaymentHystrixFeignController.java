package com.smj.controller;

import com.smj.service.PaymentHystrixFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class PaymentHystrixFeignController {
    @Resource
    private PaymentHystrixFeignService paymentHystrixFeignService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id){
        return paymentHystrixFeignService.paymentOk(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") Integer id){
        return paymentHystrixFeignService.psymentTimeout(id);
    }
}
