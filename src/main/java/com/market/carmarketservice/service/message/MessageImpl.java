package com.market.carmarketservice.service.message;

import org.springframework.stereotype.Service;

@Service
public class MessageImpl implements MessageService {
    @Override
    public String useIsExist() {
        return "User is exist";
    }

    @Override
    public String updateUserSuccesses() {
        return "User is updated successfully";
    }

    @Override
    public String deleteUserSuccesses() {
        return "User is deleted successfully";
    }
}
