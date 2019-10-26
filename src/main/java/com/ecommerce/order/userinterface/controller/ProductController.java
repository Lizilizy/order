/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.userinterface.controller;

import static com.google.common.collect.ImmutableMap.of;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.product.ProductApplicationService;
import com.ecommerce.order.product.command.CreateProductCommand;
import com.ecommerce.order.infrastructure.repo.ProductRepository;

/**
 * 卖家 controller
 *
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-25
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductApplicationService productApplicationService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Map<String, String> createOrder(@RequestBody @Valid CreateProductCommand command) {
        return of("id", productApplicationService.createOrder(command));
    }

}
