package com.ecommerce.order.order.command;

import static com.google.common.collect.Lists.newArrayList;

import java.math.BigDecimal;

import com.ecommerce.order.order.model.Address;
import com.ecommerce.order.infrastructure.utils.GsonUtils;

public class Test {
    public static void main(String[] args) {

        ChangeProductCountCommand changeProductCountCommand = new ChangeProductCountCommand("12345", 30);
        System.out.println(GsonUtils.toJson(changeProductCountCommand));

        Address address = Address.of("上海", "上海", "曹安公路4800号");
        ChangeAddressCommand addressCommand = new ChangeAddressCommand("上海", "上海", "曹安公路4800号");
        System.out.println(GsonUtils.toJson(addressCommand));

        CreateOrderCommand createOrderCommand =
                new CreateOrderCommand(newArrayList(new OrderItemCommand("12345", 2, BigDecimal.valueOf(20))), address,
                        "buyer_1", "seller_1");
        System.out.println(GsonUtils.toJson(createOrderCommand));

    }
}
