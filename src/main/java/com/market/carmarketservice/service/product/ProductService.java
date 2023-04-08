package com.market.carmarketservice.service.product;

import com.market.carmarketservice.model.product.Products;

import java.util.List;

public interface ProductService {
    public abstract List<Products> getProducts();

    public abstract Products getProduct(int id);

    public boolean createProduct(Products products);

    public boolean updateProduct(Products products, int id);

    public abstract boolean deleteProduct(int id);

    public abstract boolean existProduct(int id);

}
