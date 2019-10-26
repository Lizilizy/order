/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.utils;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.OrderItemCommand;
import com.ecommerce.order.order.model.Address;
import com.ecommerce.order.userinterface.controller.BuyerOrderController;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-26
 */
@Component
public class ConcurrencyTest {

    @Autowired
    private BuyerOrderController buyerOrderController;

    public static void main(String[] args) throws InterruptedException {
        Runnable taskTemp = new Runnable() {

            // 注意，此处是非线程安全的，留坑
            private int iCounter;

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 发起请求
                    //                    HttpClientOp.doGet("https://www.baidu.com/");

                    iCounter++;
                    System.out.println(
                            System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ConcurrencyTest latchTest = new ConcurrencyTest();
        latchTest.startTaskAllInOnce(1, taskTemp);
    }

    private CreateOrderCommand buildCreateOrderCommand(String buyerId) {
        Address address = Address.of("上海", "上海", "曹安公路4800号");
        return new CreateOrderCommand(
                Lists.newArrayList(new OrderItemCommand("12345", 1, BigDecimal.valueOf(5000))),
                address, buyerId, "seller_1");
    }

    /**
     * 线程数量
     */
    public void highConcurrencyTest(int threadNums) throws InterruptedException {
        Runnable taskTemp = () -> buyerOrderController.createOrder(buildCreateOrderCommand("test"));
        ConcurrencyTest latchTest = new ConcurrencyTest();
        latchTest.startTaskAllInOnce(threadNums, taskTemp);
    }

    public long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for (int i = 0; i < threadNums; i++) {
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-" + i).build();
            ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

            singleThreadPool.execute(() -> task(task, startGate, endGate));
        }

        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        return endTime - startTime;
    }

    private void task(Runnable task, CountDownLatch startGate, CountDownLatch endGate) {
        try {
            // 使线程在此等待，当开始门打开时，一起涌入门中
            startGate.await();
            try {
                task.run();
            } finally {
                // 将结束门减1，减到0时，就可以开启结束门了
                endGate.countDown();
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

}
