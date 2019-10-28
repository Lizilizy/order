package com.ecommerce.order.order.command;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ecommerce.order.order.model.Address;

import lombok.Value;

/**
 * command 用于封装请求，一般入参大于等于3个时候使用
 * 添加@Value注解，类属性会被编译成final的，因此只有get方法，而没有set方法
 */

@Value
public class CreateOrderCommand {

    @Valid
    @NotEmpty(message = "订单项不能为空")
    private List<OrderItemCommand> items;

    @NotNull(message = "订单地址不能为空")
    private Address address;

    @NotNull(message = "买家ID不能为空")
    private String buyerId;

    @NotNull(message = "卖家ID不能为空")
    private String sellerId;
}
