/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.utils.page;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.data.domain.Page;

import com.google.common.collect.Lists;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
public class PageUtils {

    public static <T> PageResource<T> convertToPageResource(Page<T> jpaPage) {

        Validate.isTrue(jpaPage.getSize() > -1, "pageSize has not been set");
        Validate.isTrue(jpaPage.getNumber() > -1, "pageNo has not been set");
        Validate.isTrue((int) jpaPage.getTotalElements() > -1, "totalCount has not been set");
        Validate.notNull(jpaPage.getContent(), "list has not been set");

        List<OrderBy> orderByList = Lists.newArrayList();
        if (jpaPage.getSort() != null) {
            jpaPage.getSort().forEach(s -> {
                OrderBy.OrderType orderType;
                if (s.getDirection().isAscending()) {
                    orderType = OrderBy.OrderType.ASC;
                } else {
                    orderType = OrderBy.OrderType.DESC;
                }
                OrderBy orderBy = new OrderBy(s.getProperty(), orderType);
                orderByList.add(orderBy);
            });
        }

        return new PageResource<T>(jpaPage.getSize(), jpaPage.getNumber() + 1, (int) jpaPage.getTotalElements(),
                jpaPage.getContent(), orderByList);
    }

}
