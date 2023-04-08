package com.market.carmarketservice.model.product;

import com.market.carmarketservice.model.product.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Integer> {
    boolean existsProductsById(int id);
}
