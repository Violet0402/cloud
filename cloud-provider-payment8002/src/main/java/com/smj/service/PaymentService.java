package com.smj.service;

import com.smj.entities.Payment;

/**
 * @Author: Administrator
 * @Description: TODO
 * @Date: 2020/08/20 15:53
 */
public interface PaymentService {
    Payment getPaymentById(Long id);

    Integer create(Payment payment);

    void createEmo(String content);
}
