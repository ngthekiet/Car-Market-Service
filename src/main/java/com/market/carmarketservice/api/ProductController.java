package com.market.carmarketservice.api;

import com.market.carmarketservice.model.product.Product;
import com.market.carmarketservice.request.search.ProductRequest;
import com.market.carmarketservice.service.message.MessageService;
import com.market.carmarketservice.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class ProductController {
    private final ProductService productService;
    private final MessageService messageService;

    @RequestMapping(value = "/pub/products", method = RequestMethod.GET)
    public ResponseEntity<Object> getProducts() {
        try {
            return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(messageService.notFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/pub/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(@PathVariable("id") int id) {
        if (productService.getProduct(id) != null)
            return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
        return new ResponseEntity<>(messageService.notFound(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pub/productBrand/{cid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductByBrand(@PathVariable("cid") int cid) {
        if (productService.getProductByBrand(cid) != null)
            return new ResponseEntity<>(productService.getProductByBrand(cid), HttpStatus.OK);
        return new ResponseEntity<>(messageService.notFound(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pri/product", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        if (productService.createProduct(product))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/pri/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        if (productService.updateProduct(product, id))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/pri/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") int id) {
        if (productService.deleteProduct(id))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/pub/search", method = RequestMethod.POST)
    public ResponseEntity<Object> findAll(@RequestBody ProductRequest request) {
        if (productService.searchProducts(request.getText()) != null)
            return new ResponseEntity<>(productService.searchProducts(request.getText()), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }
}
