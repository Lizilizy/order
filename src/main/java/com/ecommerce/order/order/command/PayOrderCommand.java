package com.ecommerce.order.order.command;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PayOrderCommand {

    @NotNull(message = "支付金额不能为空")
    private BigDecimal paidPrice;
}
