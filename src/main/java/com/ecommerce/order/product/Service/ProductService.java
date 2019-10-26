/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.product.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.order.product.model.Product;
import com.ecommerce.order.product.model.ProductPurchaseItem;
import com.ecommerce.order.infrastructure.exception.ServiceException;
import com.ecommerce.order.infrastructure.repo.ProductRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * 考虑高并发的情况，会存在超发的情况
     * 三种方案：悲观锁，乐观锁，Redis方案
     * 这里采用 乐观锁，考虑到并发情况，乐观锁可能存在大量失败，可以升级为 可重入的乐观锁
     */
    private boolean purchaseSinge(String productId, int quantity) {
        // 获取产品
        Product product = productRepository.findByProductId(productId);
        // 扣减数量
        boolean success = product.decreaseProduct(quantity);
        // 落库
        if (success) {
            int i = productRepository.reduceStock(quantity, productId);
            if (i != 1) {
                throw new ServiceException(String.format("该产品已售完，product(%s)", productId));
            }
        }
        return success;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean purchase(List<ProductPurchaseItem> productPurchaseItems) {

        for (ProductPurchaseItem it : productPurchaseItems) {
            boolean sucesss = purchaseSinge(it.getProductId(), it.getQuantity());
            if (!sucesss) {
                throw new ServiceException(String.format("产品(%s)库存不足，购买失败", it.getProductId()));
            }
        }

        return true;

    }
}
