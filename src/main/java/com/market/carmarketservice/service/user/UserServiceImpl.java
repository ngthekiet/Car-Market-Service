package com.market.carmarketservice.service.user;

import com.market.carmarketservice.user.User;
import com.market.carmarketservice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        Optional<User> optional = userRepository.findById(id);
        User user = optional.get();
        return user;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isUser(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existUser(String username) {
        return userRepository.existsUserByUsername(username);
    }
}
