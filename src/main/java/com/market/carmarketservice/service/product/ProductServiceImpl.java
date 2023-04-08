package com.market.carmarketservice.service.product;

import com.market.carmarketservice.model.product.ProductRepository;
import com.market.carmarketservice.model.product.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Products> getProducts() {
        return (List<Products>) productRepository.findAll();
    }

    @Override
    public Products getProduct(int id) {
        if (existProduct(id)) {
            Optional<Products> optional = productRepository.findById(id);
            Products product = optional.get();
            return product;
        }
        return null;
    }

    @Override
    public boolean createProduct(Products products) {
        try {
            var product = Products.builder()
                    .name(products.getName())
                    .image(products.getImage())
                    .price(products.getPrice())
                    .categories(products.getCategories())
                    .build();
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProduct(Products products, int id) {
        if (existProduct(id)) {
            products.setId(id);
            productRepository.save(products);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        if (existProduct(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existProduct(int id) {
        return productRepository.existsProductsById(id);
    }
}
