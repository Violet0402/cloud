package com.smj.service;

import com.smj.service.impl.PaymentHystrixFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(fallback = PaymentHystrixFeignFallBack.class,name = "CLOUD-PAYMENT-HYSTRIX-SERVICE")
public interface PaymentHystrixFeignService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String psymentTimeout(@PathVariable("id") Integer id);


}
