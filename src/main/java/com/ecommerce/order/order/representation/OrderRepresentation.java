package com.ecommerce.order.order.representation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ecommerce.order.order.model.Address;
import com.ecommerce.order.order.model.OrderItem;

import lombok.Builder;
import lombok.Data;

/**
 * 订单详情 展示封装类
 */
@Data
@Builder
public class OrderRepresentation {
    private String orderId;
    private List<OrderItem> items;
    private BigDecimal totalPrice;
    private String status;
    private Address address;
    private Date createdAt;
}
