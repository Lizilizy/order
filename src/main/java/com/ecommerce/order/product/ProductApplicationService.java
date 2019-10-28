package com.ecommerce.order.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.order.product.command.CreateProductCommand;
import com.ecommerce.order.product.model.Product;
import com.ecommerce.order.product.model.ProductFactory;
import com.ecommerce.order.infrastructure.repo.ProductRepository;
import com.ecommerce.order.infrastructure.utils.GsonUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductApplicationService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductFactory productFactory;

    /**
     * 新建产品
     */
    @Transactional
    public String createProduct(CreateProductCommand command) {
        Product product = productFactory.create(command.getProductId(), command.getProductName(), command.getStock(),
                command.getPrice(), command.getSellerId());
        productRepository.save(product);
        log.info("insert product[{}].detail:{}", product.getProductId(), GsonUtils.toJson(product));
        return product.getProductId();
    }
}
