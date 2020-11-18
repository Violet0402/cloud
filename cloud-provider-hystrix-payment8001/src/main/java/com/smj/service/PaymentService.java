package com.smj.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String paymentInfo_ok(Integer id){
        return "线程池:  " + Thread.currentThread().getName()+"  paymentInfo_ok,id:"+id+"\t"+"哈哈哈";
    }
}
