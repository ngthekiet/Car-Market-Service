package com.market.carmarketservice.controller;

import com.market.carmarketservice.model.product.Products;
import com.market.carmarketservice.service.message.MessageService;
import com.market.carmarketservice.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MessageService messageService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<Object> getProducts() {
        try {
            return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(messageService.productNotFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(@PathVariable("id") int id) {
        if (productService.getProduct(id) != null)
            return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(messageService.productNotFound(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Products products) {
        if (productService.createProduct(products))
            return new ResponseEntity<>(messageService.createProductSuccesses(), HttpStatus.OK);
        else
            return new ResponseEntity<>(messageService.createProductFail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Products products) {
        if (productService.updateProduct(products, id))
            return new ResponseEntity<>(messageService.updateProductSuccesses(), HttpStatus.OK);
        else
            return new ResponseEntity<>(messageService.updateProductFail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") int id) {
        if (productService.deleteProduct(id))
            return new ResponseEntity<>(messageService.deleteProductSuccesses(), HttpStatus.OK);
        else
            return new ResponseEntity<>(messageService.deleteProductFail(), HttpStatus.BAD_REQUEST);
    }
}
