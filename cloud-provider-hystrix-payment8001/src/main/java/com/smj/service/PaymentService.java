package com.smj.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_ok(Integer id){
        return "线程池:  " + Thread.currentThread().getName()+"  paymentInfo_ok,id:"+id+"\t"+"哈哈哈";
    }

    /**
     * 超时访问
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName()+"  paymentInfo_timeout,id:"+id+"\t"+"哈哈哈";
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return "线程池:  " + Thread.currentThread().getName()+"  paymentInfoTimeoutHandler,id:"+id+"\t"+"哈哈哈";
    }
}
