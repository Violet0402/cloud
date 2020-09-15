package com.smj.controller;

import com.smj.entities.CommonResult;
import com.smj.entities.Payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import com.smj.service.PaymentService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Administrator
 * @Description: TODO
 * @Date: 2020/08/20 15:54
 */
@RequestMapping("/payment")
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/get/{id}")
    public CommonResult getPayment(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("获取的payment:" + payment);
        if (payment != null){
            return new CommonResult(200, "获取成功-serverPort:"+serverPort, payment);
        }
        return new CommonResult(444, "没有对应记录");
    }

    @PostMapping("/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        Integer i = paymentService.create(payment);
        log.info("保存成功"+ i);
        if (i > 0){
            return new CommonResult(200, "创建成功-serverPort:"+serverPort, i);
        }else{
            return new CommonResult(444, "创建失败");
        }
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(s -> log.info("******element:"+s));

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(i -> log.info(i.getServiceId() + "\t" + i.getHost() + "\t" + i.getPort() + "\t" + i.getUri()));

        return this.discoveryClient;
    }

    @PostMapping("createEmo")
    public CommonResult createEmo(String content){
        paymentService.createEmo(content);
        return new CommonResult(200,"成功");
    }
}
