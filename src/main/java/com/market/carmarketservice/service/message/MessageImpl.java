package com.market.carmarketservice.service.message;

import org.springframework.stereotype.Service;

@Service
public class MessageImpl implements MessageService {
    @Override
    public String userIsExist() {
        return "User is exist";
    }

    @Override
    public String notFound() {
        return "Not found";
    }

    @Override
    public String fail() {
        return "Fail";
    }

    @Override
    public String successes() {
        return "Successes";
    }
}
