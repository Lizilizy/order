package com.ecommerce.order.infrastructure.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.infrastructure.Entity.OrderEntity;
import com.ecommerce.order.infrastructure.exception.ServiceException;

/**
 * order Repository
 */
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    /**
     * 通过订单ID 获取订单
     *
     * @param orderId 订单ID
     * @return 订单
     */
    default Order byId(String orderId) {
        OrderEntity orderEntity = findByOrderId(orderId);
        if (orderEntity != null) {
            return Order.createFromDB(orderEntity);
        } else {
            throw new ServiceException("Order Not found, orderId:" + orderId);
        }
    }

    /**
     * find by order ID
     *
     * @param orderId 订单号ID
     * @return OrderEntity
     */
    OrderEntity findByOrderId(String orderId);

    /**
     * find by buyer id
     *
     * @param buyerId 买家ID
     * @return OrderEntity
     */
    Page<OrderEntity> findByBuyerId(String buyerId, Pageable pageable);

    /**
     * find by seller id
     *
     * @param sellerId 卖家ID
     * @return OrderEntity
     */
    Page<OrderEntity> findBySellerId(String sellerId, Pageable pageable);
}
