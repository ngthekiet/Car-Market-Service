package com.market.carmarketservice.service.user;

import com.market.carmarketservice.request.auth.AuthenticationRequest;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.request.user.UserRequest;
import com.market.carmarketservice.response.user.UserResponse;

import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public UserResponse getUser(int id);

    public boolean updateUser(User user, int id);

    public boolean deleteUser(int id);

    public boolean isUser(int id);

    public boolean existUser(String username);

    public boolean validPassword(AuthenticationRequest request);

    public boolean changeAvatar(UserRequest avatar, int id);
}
