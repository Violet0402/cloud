package com.smj.service.impl;

import com.smj.entities.Payment;
import org.springframework.stereotype.Service;
import com.smj.dao.PaymentDao;
import com.smj.service.PaymentService;

import javax.annotation.Resource;

/**
 * @Author: Administrator
 * @Description: TODO
 * @Date: 2020/08/20 15:53
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public Integer create(Payment payment) {
        return paymentDao.create(payment);
    }


}
