package com.smj.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.smj.service.PaymentHystrixFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "paymentInfoTimeoutHandler_global")
public class PaymentHystrixFeignController {
    @Resource
    private PaymentHystrixFeignService paymentHystrixFeignService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id){
        return paymentHystrixFeignService.paymentOk(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand
    public String paymentTimeout(@PathVariable("id") Integer id){
        return paymentHystrixFeignService.psymentTimeout(id);
    }

    public String paymentInfoTimeoutHandler_global(Integer id){
        return "线程池:  " + Thread.currentThread().getName()+"  paymentInfoTimeoutHandler,id:"+id+"\t"+"哈哈哈";
    }
}
