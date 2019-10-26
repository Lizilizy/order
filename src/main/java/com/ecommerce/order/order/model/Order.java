/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.order.model;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.order.product.model.ProductPurchaseItem;
import com.ecommerce.order.order.exception.OrderCannotBeModifiedException;
import com.ecommerce.order.order.exception.PaidPriceNotSameWithOrderPriceException;
import com.ecommerce.order.order.exception.ProductNotInOrderException;
import com.ecommerce.order.order.representation.OrderListRepresentation;
import com.ecommerce.order.order.representation.OrderRepresentation;
import com.ecommerce.order.infrastructure.Entity.OrderEntity;
import com.ecommerce.order.infrastructure.exception.UnexpectedException;
import com.ecommerce.order.infrastructure.utils.GsonUtils;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单 领域类
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-25
 */
@Data
@Builder
@Slf4j
public class Order {

    private Long id;
    private String orderId;
    private String buyerId;
    private String sellerId;

    private BigDecimal totalPrice;
    private OrderStatus status;
    private Date createdAt;

    private List<OrderItem> items;
    private Address address;

    public static Order create(String orderId, List<OrderItem> items, Address address, String buyerId,
                               String sellerId) {
        return Order.builder()
                .orderId(orderId)
                .buyerId(buyerId)
                .sellerId(sellerId)
                .items(items)
                .totalPrice(calculateTotalPrice(items))
                .status(OrderStatus.CREATED)
                .address(address)
                .createdAt(new Date())
                .build();
    }

    public static Order createFromDB(OrderEntity orderEntity) {
        if (orderEntity == null) {
            throw new UnexpectedException("orderEntity can not be null");
        }
        OrderDetail orderDetail = OrderDetail.createfromJson(orderEntity.getDetails());
        return Order.builder()
                .id(orderEntity.getId())
                .orderId(orderEntity.getOrderId())
                .sellerId(orderEntity.getSellerId())
                .buyerId(orderEntity.getBuyerId())
                .totalPrice(orderEntity.getTotalPrice())
                .status(orderEntity.getStatus())
                .items(orderDetail.getOrderItems())
                .address(orderDetail.getAddress())
                .createdAt(orderEntity.getCreateTime())
                .build();
    }

    private static BigDecimal calculateTotalPrice(List<OrderItem> items) {
        return items.stream()
                .map(OrderItem::totalPrice)
                .reduce(ZERO, BigDecimal::add);
    }

    /**
     * 修改 指定产品的数量
     */
    public void changeProductCount(String productId, int count) {
        if (this.status == OrderStatus.PAID) {
            throw new OrderCannotBeModifiedException(this.orderId);
        }

        OrderItem orderItem = searchOrderItem(productId);
        int originalCount = orderItem.getCount();
        orderItem.updateCount(count);
        this.totalPrice = calculateTotalPrice(items);

        log.info("[change product count], orderId:{}, productId:{}, count from {} to {}",
                this.getOrderId(), productId, originalCount, count);
    }

    /**
     * 搜索 指定商品
     */
    private OrderItem searchOrderItem(String productId) {
        return items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotInOrderException(productId, orderId));
    }

    /**
     * 修改地址
     */
    public void changeAddress(Address address) {
        if (this.status == OrderStatus.PAID) {
            throw new OrderCannotBeModifiedException(this.orderId);
        }
        this.address = address;
    }

    /**
     * 付款
     */
    public void pay(BigDecimal paidPrice) {
        if (status != OrderStatus.CREATED) {
            throw new UnexpectedException(String.format("when pay, The order status is not CREATED，orderId:%s",
                    this.orderId));
        }
        if (this.totalPrice.compareTo(paidPrice) != 0) {
            throw new PaidPriceNotSameWithOrderPriceException(orderId);
        }
        this.status = OrderStatus.PAID;
    }

    /**
     * 确认发货
     */
    public void deliverGoods() {
        if (status != OrderStatus.PAID) {
            log.error("when deliver goods , The order status is not PAID");
            throw new UnexpectedException("when deliver goods , The order status is not PAID");
        }
        this.status = OrderStatus.DELIVERED;
    }

    /**
     * 确认收货
     */
    public void confirmReceipt() {
        if (status != OrderStatus.DELIVERED) {
            log.error("when confirm Receipt , The order status is not DELIVERED");
            throw new UnexpectedException("when confirm Receipt , The order status is not DELIVERED");
        }
        this.status = OrderStatus.RECEIVED;
    }

    /**
     * 订单 detail信息
     */
    public OrderRepresentation toRepresentation() {
        return OrderRepresentation.builder()
                .orderId(this.orderId)
                .items(this.items)
                .totalPrice(this.totalPrice)
                .status(this.status.name())
                .address(this.address)
                .createdAt(this.createdAt)
                .build();
    }

    /**
     * 订单 List信息
     */
    public OrderListRepresentation toListRepresentation() {
        return OrderListRepresentation.builder()
                .orderId(this.orderId)
                .totalPrice(this.totalPrice)
                .status(this.status.name())
                .address(this.address)
                .createdAt(this.createdAt)
                .build();
    }

    /**
     * 转为 实体
     */
    public OrderEntity toOrderEntity() {
        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(this.orderId)
                .buyerId(this.buyerId)
                .sellerId(this.sellerId)
                .totalPrice(this.totalPrice)
                .status(this.status)
                .details(GsonUtils.toJson(OrderDetail.of(items, address)))
                .build();
        orderEntity.setId(this.id);
        orderEntity.setCreateTime(createdAt == null ? new Date() : createdAt);
        orderEntity.setUpdateTime(new Date());
        return orderEntity;
    }

    public List<ProductPurchaseItem> toProductPurchaseItem() {
        return items.stream().map(OrderItem::tpProductPurchaseItem).collect(Collectors.toList());
    }
}
