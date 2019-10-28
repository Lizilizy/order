package com.ecommerce.order.order.model;

import java.math.BigDecimal;

import com.ecommerce.order.product.model.ProductPurchaseItem;
import com.google.common.base.Preconditions;

import lombok.Builder;
import lombok.Data;

/**
 * 订单子项
 */
@Data
@Builder
public class OrderItem {

    /**
     * 产品ID, 一个订单子项 对应 一个产品
     */
    private String productId;
    /**
     * 数目
     */
    private int count;
    /**
     * 产品价格
     */
    private BigDecimal itemPrice;

    public static OrderItem create(String productId, int count, BigDecimal itemPrice) {
        Preconditions.checkArgument(count >= 0 && itemPrice.compareTo(BigDecimal.ZERO) >= 0);
        return OrderItem.builder()
                .productId(productId)
                .count(count)
                .itemPrice(itemPrice)
                .build();
    }

    /**
     * 计算总价
     */
    BigDecimal totalPrice() {
        return itemPrice.multiply(BigDecimal.valueOf(count));
    }

    /**
     * 更新数量
     */
    public void updateCount(int count) {
        if (count < 0) {
            return;
        }
        this.count = count;
    }

    public ProductPurchaseItem tpProductPurchaseItem() {
        return new ProductPurchaseItem(this.productId, this.count);
    }

}
