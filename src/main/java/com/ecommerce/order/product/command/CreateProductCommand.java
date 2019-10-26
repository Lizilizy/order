/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.product.command;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Value;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */

@Value
public class CreateProductCommand {
    @Valid
    @NotEmpty(message = "产品ID不能为空")
    private String productId;

    @NotNull(message = "产品名称不能为空")
    private String productName;

    @Min(value = 1, message = "产品数量必须大于0")
    private int stock;

    @NotNull(message = "产品单价不能为空")
    private BigDecimal price;

    @NotNull(message = "卖家ID不能为空")
    private String sellerId;
}
