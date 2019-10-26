/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.userinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.infrastructure.utils.ConcurrencyTest;
import com.ecommerce.order.infrastructure.common.CommonResponse;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@RestController
@RequestMapping(value = "/system/concurrent")
public class TestController {
    @Autowired
    private ConcurrencyTest latchTest;

    @PostMapping
    public String testConcurrent(@RequestParam int threadNum) throws InterruptedException {
        latchTest.highConcurrencyTest(threadNum);
        return CommonResponse.success();
    }
}
