package com.smj.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
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
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id){
        /*int age = 10/0;*/
        try {

            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName()+"  paymentInfo_timeout,id:"+id+"\t"+"哈哈哈";
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return "线程池:  " + Thread.currentThread().getName()+"  paymentInfoTimeoutHandler,id:"+id+"\t"+"哈哈哈";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("id不能为复数");
        }
        String replace = UUID.randomUUID().toString().replace("-", "");
        return Thread.currentThread().getName()+"\t"+"调用成功:"+replace;
    }

    //服务熔断
    public String paymentCircuitBreaker_Fallback(@PathVariable("id") Integer id){
        return "id不能为空，请稍后再试------>id:"+id;
    }
}
