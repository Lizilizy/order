package com.ecommerce.order.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.order.order.command.ChangeAddressCommand;
import com.ecommerce.order.order.command.ChangeProductCountCommand;
import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.PayOrderCommand;
import com.ecommerce.order.order.model.Address;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderFactory;
import com.ecommerce.order.order.model.OrderItem;
import com.ecommerce.order.order.representation.OrderRepresentation;
import com.ecommerce.order.order.service.OrderPaymentService;
import com.ecommerce.order.order.service.OrderRepresentationService;
import com.ecommerce.order.product.Service.ProductService;
import com.ecommerce.order.product.model.ProductPurchaseItem;
import com.ecommerce.order.infrastructure.Entity.OrderEntity;
import com.ecommerce.order.infrastructure.exception.UnexpectedException;
import com.ecommerce.order.infrastructure.repo.OrderRepository;
import com.ecommerce.order.infrastructure.utils.GsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * order application service
 */
@Service
@Slf4j
public class OrderApplicationService {
    @Autowired
    private OrderFactory orderFactory;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderRepresentationService representationService;
    @Autowired
    private OrderPaymentService orderPaymentService;
    @Autowired
    private ProductService productService;

    /**
     * 查询指定ID的订单
     */
    @Transactional
    public String byOrderId(String orderId) {
        OrderRepresentation orderRepresentation = representationService.byId(orderId);
        return GsonUtils.toJson(orderRepresentation);
    }

    /**
     * 修改 订单
     */
    @Transactional
    public void changeProductCount(String orderId, ChangeProductCountCommand command) {
        Order order = orderRepository.byId(orderId);
        OrderItem orderItem = order.searchOrderItem(command.getProductId());
        int originalCount = orderItem.getCount();
        boolean purchase = productService.purchaseSingle(command.getProductId(), command.getCount()-originalCount);
        if (!purchase) {
            throw new UnexpectedException();
        }
        order.changeProductCount(command.getProductId(), command.getCount());
        orderRepository.save(order.toOrderEntity());
    }

    /**
     * 修改 地址
     */
    @Transactional
    public void changeAddressDetail(String orderId, ChangeAddressCommand command) {
        Order order = orderRepository.byId(orderId);
        order.changeAddress(Address.of(command.getProvince(), command.getCity(), command.getDetail()));
        orderRepository.save(order.toOrderEntity());
    }

    /**
     * 下单
     */
    @Transactional
    public String createOrder(CreateOrderCommand command) {

        List<OrderItem> items = command.getItems().stream()
                .map(item -> OrderItem.create(item.getProductId(),
                        item.getCount(),
                        item.getItemPrice()))
                .collect(Collectors.toList());

        // 更新库存数量
        boolean purchase = productService.purchase(ProductPurchaseItem.createProductPurchaseItems(items));
        if (!purchase) {
            throw new UnexpectedException();
        }

        // 创建订单
        Order order = orderFactory.create(items, command.getAddress(), command.getBuyerId(), command.getSellerId());
        OrderEntity orderEntity = order.toOrderEntity();
        orderRepository.save(orderEntity);
        log.info("buyer[{}] created order[{}]. order detail is: {}", order.getBuyerId(), order.getOrderId(),
                GsonUtils.toJson(order.toRepresentation()));
        return order.getOrderId();
    }

    /**
     * 付款
     */
    @Transactional
    public void pay(String orderId, PayOrderCommand command) {
        Order order = orderRepository.byId(orderId);
        // 扣款
        orderPaymentService.pay(order, command.getPaidPrice());
        orderRepository.save(order.toOrderEntity());
    }

    /**
     * 发货
     */
    @Transactional
    public void deliverGoods(String orderId) {
        Order order = orderRepository.byId(orderId);
        // todo:: 填写发货信息
        // 确认发货
        order.deliverGoods();
        orderRepository.save(order.toOrderEntity());
    }

    /**
     * 确认收货
     */
    @Transactional
    public void confirmReceipt(String orderId) {
        Order order = orderRepository.byId(orderId);
        order.confirmReceipt();
        orderRepository.save(order.toOrderEntity());
    }

}
