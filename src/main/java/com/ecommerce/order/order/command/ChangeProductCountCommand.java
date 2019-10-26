/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.command;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Value;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@Value
public class ChangeProductCountCommand {
    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @Min(value = 1, message = "产品数量必须大于0")
    private int count;

}
