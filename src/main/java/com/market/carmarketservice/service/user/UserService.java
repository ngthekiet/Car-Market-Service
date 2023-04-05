package com.market.carmarketservice.service.user;

import com.market.carmarketservice.bean.user.User;

import java.util.List;

public interface UserService {
    public abstract boolean updateUser(User user, int id);

    public abstract User getUser(int id);

    public abstract List<User> getUsers();

    public abstract boolean deleteUser(int id);

    public abstract boolean isUser(int id);

    public abstract boolean existUser(String username);
}
