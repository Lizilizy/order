/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.command;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Value;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@Value
public class OrderItemCommand {
    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @Min(value = 1, message = "产品数量必须大于0")
    private int count;

    @NotNull(message = "产品单价不能为空")
    private BigDecimal itemPrice;
}
