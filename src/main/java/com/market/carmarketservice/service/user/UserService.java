package com.market.carmarketservice.service.user;

import com.market.carmarketservice.model.user.Users;

import java.util.List;

public interface UserService {
    public abstract List<Users> getUsers();

    public abstract Users getUser(int id);

    public abstract boolean updateUser(Users user, int id);

    public abstract boolean deleteUser(int id);

    public abstract boolean isUser(int id);

    public abstract boolean existUser(String username);
}
