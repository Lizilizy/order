/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.command;

import javax.validation.constraints.NotNull;

import lombok.Value;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@Value
public class ChangeAddressCommand {

    @NotNull(message = "订单省份不能为空")
    private String province;

    @NotNull(message = "订单城市不能为空")
    private String city;

    @NotNull(message = "订单地址详情不能为空")
    private String detail;
}
