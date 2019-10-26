/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.utils.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;

/**
 * Page createfromJson specified type {@code T}, this class is generally used as <b>output</b> parameter createfromJson process (web service,
 * function, etc.).
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
public final class PageResource<T> {

    private int pageSize;

    private int pageNo;

    private int totalCount;

    private List<T> list;

    private List<OrderBy> orderBys;

    /**
     * Used By PageBuilders
     *
     * @param pageSize
     * @param pageNo
     * @param totalCount
     * @param list
     * @param orderBys
     */
    PageResource(int pageSize, int pageNo, int totalCount, List<T> list, List<OrderBy> orderBys) {
        Validate.isTrue(pageNo > 0, "pageNo should be greater than 0");
        Validate.isTrue(pageSize > 0, "pageSize should be greater than 0");
        Validate.isTrue(totalCount >= 0, "totalCount should be greater than or equal 0");
        Validate.notNull(list);

        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalCount = totalCount;
        this.list = list == null ? null : new ArrayList<T>(list);
        this.orderBys = orderBys == null ? null : new ArrayList<OrderBy>(orderBys);

    }

    /**
     * list中的bean手工join UserInfo等信息后,用这个构造新的Page
     */
    public PageResource(PageResource<?> inputPage, List<T> list) {
        this(inputPage.pageSize(), inputPage.pageNo, inputPage.totalCount(), list, inputPage.orderBys());
    }

    public int pageSize() {
        return pageSize;
    }

    public int pageNo() {
        return pageNo;
    }

    public List<OrderBy> orderBys() {
        return orderBys == null ? null : Collections.unmodifiableList(orderBys);
    }

    public List<T> list() {
        return list == null ? null : Collections.unmodifiableList(list);
    }

    public int totalCount() {
        return totalCount;
    }

    public int totalPages() {
        int totalPages = totalCount / pageSize;
        if (this.totalCount % this.pageSize > 0L) {
            totalPages += 1L;
        }
        return totalPages;
    }

    public boolean hasNext() {
        return (this.pageNo + 1 <= totalPages());
    }

    public int nextPage() {
        if (hasNext()) {
            return (this.pageNo + 1);
        }
        return this.pageNo;
    }

    // 方便Action中组装Bean
    public String toOrderByPropertyString() {
        StringBuilder sb = new StringBuilder();
        if (orderBys != null) {
            Iterator<OrderBy> it = orderBys.iterator();
            while (it.hasNext()) {
                sb.append(it.next().property());
                if (it.hasNext()) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    // 方便Action中组装Bean
    public String toOrderByTypeString() {
        StringBuilder sb = new StringBuilder();
        if (orderBys != null) {
            Iterator<OrderBy> it = orderBys.iterator();
            while (it.hasNext()) {
                sb.append(it.next().orderType().name().toLowerCase());
                if (it.hasNext()) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

}

