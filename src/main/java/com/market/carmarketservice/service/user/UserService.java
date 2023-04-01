package com.market.carmarketservice.service.user;

import com.market.carmarketservice.bean.user.User;

import java.util.List;

public interface UserService {
    public abstract void updateUser(User user);

    public abstract User getUser(int id);

    public abstract List<User> getUsers();

    public abstract void deleteUser(int id);

    public abstract boolean isUser(int id);

    public abstract boolean existUser(String username);
}
