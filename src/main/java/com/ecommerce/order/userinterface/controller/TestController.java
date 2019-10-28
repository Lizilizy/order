package com.ecommerce.order.userinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.infrastructure.utils.ConcurrencyTest;
import com.ecommerce.order.infrastructure.common.CommonResponse;

@RestController
@RequestMapping(value = "/system/concurrent")
public class TestController {
    @Autowired
    private ConcurrencyTest concurrencyTest;

    @PostMapping
    public String testConcurrent(@RequestParam int threadNum){
        return CommonResponse.success();
    }
}
