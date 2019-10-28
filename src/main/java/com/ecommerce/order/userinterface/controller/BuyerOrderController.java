package com.ecommerce.order.userinterface.controller;

import static com.ecommerce.order.infrastructure.common.CommonResponse.success;
import static com.google.common.collect.ImmutableMap.of;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.order.OrderApplicationService;
import com.ecommerce.order.order.command.ChangeAddressCommand;
import com.ecommerce.order.order.command.ChangeProductCountCommand;
import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.PayOrderCommand;
import com.ecommerce.order.order.representation.OrderListRepresentation;
import com.ecommerce.order.order.service.OrderRepresentationService;
import com.ecommerce.order.infrastructure.utils.page.PageResource;

/**
 * 买家 controller
 */
@RestController
@RequestMapping(value = "/buyer/orders")
public class BuyerOrderController {
    @Autowired
    private OrderApplicationService applicationService;
    @Autowired
    private OrderRepresentationService orderRepresentationService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Map<String, String> createOrder(@RequestBody @Valid CreateOrderCommand command) {
        return of("id", applicationService.createOrder(command));
    }

    @GetMapping("/{buyerId}/list")
    public String byOrderId(@PathVariable(name = "buyerId") String buyerId,
                            @RequestParam(required = false, defaultValue = "1") int pageIndex,
                            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageResource<OrderListRepresentation> buyerOrders =
                orderRepresentationService.getBuyerOrders(buyerId, pageIndex, pageSize);
        return success(buyerOrders);
    }

    @GetMapping("/{orderId}/detail")
    public String byId(@PathVariable(name = "orderId") String orderId) {
        return success(orderRepresentationService.byId(orderId));
    }

    @PostMapping("/{orderId}/products")
    public String changeProductCount(@PathVariable(name = "orderId") String orderId,
                                     @RequestBody @Valid ChangeProductCountCommand command) {
        applicationService.changeProductCount(orderId, command);
        return "success";
    }

    @PostMapping("/{orderId}/address")
    public String changeAddress(@PathVariable(name = "orderId") String orderId,
                              @RequestBody @Valid ChangeAddressCommand command) {
        applicationService.changeAddressDetail(orderId, command);
        return "change address success";
    }

    @PostMapping("/{orderId}/payment")
    public String pay(@PathVariable(name = "orderId") String orderId, @RequestBody @Valid PayOrderCommand command) {
        applicationService.pay(orderId, command);
        return "payment success";
    }

    @PostMapping("/{orderId}/received")
    public String confirmReceipt(@PathVariable(name = "orderId") String orderId) {
        applicationService.confirmReceipt(orderId);
        return "confirm receipt success";
    }
}
