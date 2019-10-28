package com.ecommerce.order.infrastructure.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.order.product.model.Product;

/**
 * product repository
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByProductId(String productId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Product set stock = stock -?1 where product_id=?2 and stock>0")
    int reduceStock(Integer quantity, String productId);
}
