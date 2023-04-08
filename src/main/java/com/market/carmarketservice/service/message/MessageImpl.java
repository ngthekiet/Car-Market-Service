package com.market.carmarketservice.service.message;

import org.springframework.stereotype.Service;

@Service
public class MessageImpl implements MessageService {
    @Override
    public String userIsExist() {
        return "User is exist";
    }

    @Override
    public String userNotFound() {
        return "User is not exist";
    }

    @Override
    public String updateUserSuccesses() {
        return "User is updated successfully";
    }

    @Override
    public String updateUserFail() {
        return "Can not update user";
    }

    @Override
    public String deleteUserSuccesses() {
        return "User is deleted successfully";
    }

    @Override
    public String deleteUserFail() {
        return "Can not delete product";
    }

    @Override
    public String productNotFound() {
        return "Product is not exist";
    }

    @Override
    public String createProductFail() {
        return "Can not create product";
    }

    @Override
    public String createProductSuccesses() {
        return "Product is created successfully";
    }

    @Override
    public String updateProductFail() {
        return "Can not update product";
    }

    @Override
    public String updateProductSuccesses() {
        return "Product is updated successfully";
    }

    @Override
    public String deleteProductFail() {
        return "Can not delete product";
    }

    @Override
    public String deleteProductSuccesses() {
        return "Product is deleted successfully";
    }
}
