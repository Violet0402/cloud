package com.smj.Controller;

import com.smj.entities.CommonResult;
import com.smj.entities.Payment;
import com.smj.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @GetMapping("/get/{id}")
    public CommonResult getPayment(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("获取的payment:", payment);
        if (payment != null){
            return new CommonResult(200, "成功", payment);
        }
        return new CommonResult(444, "没有对应记录");
    }

    @PostMapping("/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        Integer i = paymentService.create(payment);
        log.info("保存成功", i);
        if (i > 0){
            return new CommonResult(200, "创建成功", i);
        }else{
            return new CommonResult(444, "创建失败");
        }
    }
}
