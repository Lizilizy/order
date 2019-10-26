/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ecommerce.order.order.model.OrderStatus;
import com.ecommerce.order.order.model.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 订单 领域类
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-25
 */
@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_table", uniqueConstraints = @UniqueConstraint(columnNames = {"orderId"}))
public class OrderEntity extends AutoIncLongIdEntity {

    /**
     * 订单号
     */
    private String orderId;
    /**
     * 买家ID
     */
    private String buyerId;
    /**
     * 卖家ID
     */
    private String sellerId;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 订单状态
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * 订单详情
     * {@link OrderDetail} json序列化后存放
     */
    private String details;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
