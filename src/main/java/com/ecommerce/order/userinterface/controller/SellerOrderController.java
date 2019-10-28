package com.ecommerce.order.userinterface.controller;

import static com.ecommerce.order.infrastructure.common.CommonResponse.success;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.order.OrderApplicationService;
import com.ecommerce.order.order.representation.OrderListRepresentation;
import com.ecommerce.order.order.service.OrderRepresentationService;
import com.ecommerce.order.infrastructure.utils.page.PageResource;

/**
 * 卖家 controller
 */
@RestController
@RequestMapping(value = "/seller/orders")
public class SellerOrderController {

    private final OrderApplicationService applicationService;
    private final OrderRepresentationService orderRepresentationService;

    @Autowired
    public SellerOrderController(OrderApplicationService applicationService,
                                 OrderRepresentationService orderRepresentationService) {
        this.applicationService = applicationService;
        this.orderRepresentationService = orderRepresentationService;
    }

    @GetMapping("/{sellerId}/list")
    public String byOrderId(@PathVariable(name = "sellerId") String sellerId,
                            @RequestParam(required = false, defaultValue = "1") int pageIndex,
                            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageResource<OrderListRepresentation> buyerOrders =
                orderRepresentationService.getSellerOrders(sellerId, pageIndex, pageSize);
        return success(buyerOrders);
    }

    @GetMapping("/{orderId}/detail")
    public String byId(@PathVariable(name = "orderId") String orderId) {
        return success(orderRepresentationService.byId(orderId));
    }

    @PostMapping("/{orderId}/deliver")
    public String deliverGoods(@PathVariable(name = "orderId") String orderId) {
        applicationService.deliverGoods(orderId);
        return success();
    }

}
