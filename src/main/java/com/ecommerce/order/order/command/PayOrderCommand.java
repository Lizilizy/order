/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.command;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@Data
public class PayOrderCommand {

    @NotNull(message = "支付金额不能为空")
    private BigDecimal paidPrice;
}
