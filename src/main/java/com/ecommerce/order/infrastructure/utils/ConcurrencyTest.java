package com.ecommerce.order.infrastructure.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ecommerce.order.userinterface.controller.BuyerOrderController;


@Component
public class ConcurrencyTest {

    @Autowired
    private BuyerOrderController buyerOrderController;

    public static void main(String[] args) {

    }

}
