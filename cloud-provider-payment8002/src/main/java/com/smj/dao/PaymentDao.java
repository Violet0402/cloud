package com.smj.dao;

import com.smj.entities.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Administrator
 * @Description: TODO
 * @Date: 2020/08/20 15:50
 */
@Mapper
public interface PaymentDao {
    @Select("select * from payment where id = #{id}")
    Payment getPaymentById(Long id);

    @Insert("insert into payment (serial) values(#{payment.serial})")
    Integer create(@Param("payment") Payment payment);
}
