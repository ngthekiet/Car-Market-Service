package com.market.carmarketservice.service.message;

public interface MessageService {
    public abstract String userIsExist();

    public String notFound();

    public String fail();

    public String successes();
}
