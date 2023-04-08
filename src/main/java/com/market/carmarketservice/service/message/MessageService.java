package com.market.carmarketservice.service.message;

public interface MessageService {
    public abstract String userIsExist();

    public abstract String userNotFound();

    public abstract String updateUserSuccesses();

    public abstract String updateUserFail();

    public abstract String deleteUserSuccesses();

    public abstract String deleteUserFail();

    public abstract String productNotFound();

    public abstract String createProductFail();

    public abstract String createProductSuccesses();

    public abstract String updateProductFail();

    public abstract String updateProductSuccesses();

    public abstract String deleteProductFail();

    public abstract String deleteProductSuccesses();
}
