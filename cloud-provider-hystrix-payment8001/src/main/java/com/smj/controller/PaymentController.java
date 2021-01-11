package com.smj.controller;

import com.smj.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment/hystrix")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_ok(id);
        log.info("serverPort:"+serverPort+"=====result:"+result);
        return result;
    }

    @GetMapping("timeout/{id}")
    public String psymentTimeout(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("serverPort:"+serverPort+"=====result:"+result);
        return result;
    }

    @GetMapping("/circuit/{id}")
    public String paymentCircuit(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("paymentCircuit接口的调用接口结果------>id:{}",id);
        return result;
    }
}
