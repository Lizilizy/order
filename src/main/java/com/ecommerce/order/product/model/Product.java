package com.ecommerce.order.product.model;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.ecommerce.order.infrastructure.Entity.AutoIncLongIdEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单 领域类
 */
@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Table(name = "product_table", uniqueConstraints = @UniqueConstraint(columnNames = {"productId"}))
public class Product extends AutoIncLongIdEntity {
    /**
     * 产品编号
     */
    private String productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 库存
     */
    private int stock;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 卖家ID
     */
    private String sellerId;
    /**
     * 版本号
     */
    @Version
    private int version;

    public static Product create(String productId, String productName, int stock, BigDecimal price, String sellerId) {
        return Product.builder()
                .productId(productId)
                .productName(productName)
                .stock(stock)
                .price(price)
                .sellerId(sellerId)
                .version(0)
                .build();
    }

    public boolean decreaseProduct(int quantity) {
        if (stock < quantity) {
            return false;
        }
        log.info("product[{}] stock from {} to {}", productId, stock, stock - quantity);
        return true;
    }
}
