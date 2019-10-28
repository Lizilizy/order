package com.ecommerce.order.order.command;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Value;

@Value
public class ChangeProductCountCommand {
    @NotBlank(message = "产品ID不能为空")
    private String productId;

    @Min(value = 1, message = "产品数量必须大于0")
    private int count;

}
