/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.utils.page;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Describe order by property and type (asc,desc)
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
public class OrderBy {
    public enum OrderType {
        ASC, DESC
    }

    private String property;

    private OrderType orderType;

    public static OrderBy desc(String property) {
        return new OrderBy(property, OrderType.DESC);
    }

    public static OrderBy asc(String property) {
        return new OrderBy(property, OrderType.ASC);
    }

    public OrderBy(final String property, final OrderType orderType) {
        Validate.notEmpty(property);
        Validate.notNull(orderType);

        this.property = property;
        this.orderType = orderType;
    }

    public String property() {
        return property;
    }

    public OrderType orderType() {
        return orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OrderBy) {
            OrderBy t = (OrderBy) o;
            return new EqualsBuilder().append(property, t.property).append(orderType, t.orderType).isEquals();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(property).append(orderType).toHashCode();
    }

    @Override
    public String toString() {
        return property + "(" + orderType + ")";
    }
}
