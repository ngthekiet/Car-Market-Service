package com.market.carmarketservice.service.product;

import com.market.carmarketservice.model.product.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();

    public Product getProduct(int id);

    public boolean createProduct(Product product);

    public boolean updateProduct(Product product, int id);

    public boolean deleteProduct(int id);

    public boolean existProduct(int id);

    public List<Product> searchProducts(String text);

}
