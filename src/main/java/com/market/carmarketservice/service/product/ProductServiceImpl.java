package com.market.carmarketservice.service.product;

import com.market.carmarketservice.model.product.ProductRepository;
import com.market.carmarketservice.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProduct(int id) {
        if (existProduct(id)) {
            Optional<Product> optional = productRepository.findById(id);
            Product product = optional.get();
            return product;
        }
        return null;
    }

    @Override
    public boolean createProduct(Product other) {
        try {
            var product = Product.builder()
                    .name(other.getName())
                    .image(other.getImage())
                    .price(other.getPrice())
                    .category(other.getCategory())
                    .build();
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product, int id) {
        if (existProduct(id)) {
            product.setId(id);
            productRepository.save(product);
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
