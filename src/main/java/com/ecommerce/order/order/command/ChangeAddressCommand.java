package com.ecommerce.order.order.command;

import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
public class ChangeAddressCommand {

    @NotNull(message = "订单省份不能为空")
    private String province;

    @NotNull(message = "订单城市不能为空")
    private String city;

    @NotNull(message = "订单地址详情不能为空")
    private String detail;
}
