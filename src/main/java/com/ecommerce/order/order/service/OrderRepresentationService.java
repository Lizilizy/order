package com.ecommerce.order.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.order.order.representation.OrderListRepresentation;
import com.ecommerce.order.order.representation.OrderRepresentation;
import com.ecommerce.order.order.exception.OrderNotFoundException;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.infrastructure.Entity.OrderEntity;
import com.ecommerce.order.infrastructure.utils.page.PageResource;
import com.ecommerce.order.infrastructure.repo.OrderRepository;
import com.ecommerce.order.infrastructure.utils.page.PageUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * order representation service
 * <p>
 * todo::入口处统一 身份校验
 */
@Component
@Slf4j
public class OrderRepresentationService {

    private final OrderRepository orderRepo;

    @Autowired
    public OrderRepresentationService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     * 查询 指定订单号 询价单
     */
    @Transactional(readOnly = true)
    public OrderRepresentation byId(String orderId) {
        OrderEntity orderEntity = orderRepo.findByOrderId(orderId);
        if (orderEntity == null) {
            throw new OrderNotFoundException(orderId);
        }
        return Order.createFromDB(orderEntity).toRepresentation();
    }

    /**
     * 查询 买家全部 订单
     */
    @Transactional(readOnly = true)
    public PageResource<OrderListRepresentation> getBuyerOrders(String buyerId, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.Direction.DESC, "id");
        Page<OrderEntity> orderEntities = orderRepo.findByBuyerId(buyerId, pageable);
        return getOrderRepresentationPageResource(orderEntities);
    }

    /**
     * 查询 卖家全部 订单
     */
    @Transactional(readOnly = true)
    public PageResource<OrderListRepresentation> getSellerOrders(String buyerId, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.Direction.DESC, "id");
        Page<OrderEntity> orderEntities = orderRepo.findBySellerId(buyerId, pageable);
        return getOrderRepresentationPageResource(orderEntities);
    }

    private PageResource<OrderListRepresentation> getOrderRepresentationPageResource(Page<OrderEntity> orderEntities) {
        PageResource<OrderEntity> orderEntityPageResource = PageUtils.convertToPageResource(orderEntities);

        List<OrderListRepresentation> orderRepresentationList = orderEntityPageResource.list().stream()
                .map(it -> Order.createFromDB(it).toListRepresentation())
                .collect(Collectors.toList());

        return new PageResource<>(orderEntityPageResource, orderRepresentationList);
    }
}
