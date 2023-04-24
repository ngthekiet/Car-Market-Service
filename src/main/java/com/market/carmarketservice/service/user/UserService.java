package com.market.carmarketservice.service.user;

import com.market.carmarketservice.auth.AuthenticationRequest;
import com.market.carmarketservice.model.user.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public User getUser(int id);

    public boolean updateUser(User user, int id);

    public boolean deleteUser(int id);

    public boolean isUser(int id);

    public boolean existUser(String username);

    public boolean validPassword(AuthenticationRequest request);
}
