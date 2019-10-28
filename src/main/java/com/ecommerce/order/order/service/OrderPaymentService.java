package com.ecommerce.order.order.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.order.order.model.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * 订单 付款服务 领域类
 */
@Component
@Slf4j
public class OrderPaymentService {

    @Transactional
    public void pay(Order order, BigDecimal paidPrice) {
        order.pay(paidPrice);
        // 调用 付款接口，实际付款
        log.info("buyer({}) pays money:({}) for order({})", order.getBuyerId(), order.getTotalPrice(),
                order.getOrderId());
    }
}
