package com.market.carmarketservice.service.user;

import com.market.carmarketservice.model.user.Users;
import com.market.carmarketservice.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean updateUser(Users user, int id) {
        Optional<Users> userOld = userRepository.findById(id);
        if (isUser(id)) {
            user.setId(id);
            user.setUsername(userOld.get().getUsername());
            user.setPassword(userOld.get().getPassword());
            user.setRole(userOld.get().getRole());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Users getUser(int id) {
        if (isUser(id)) {
            Optional<Users> optional = userRepository.findById(id);
            Users user = optional.get();
            return user;
        }
        return null;
    }

    @Override
    public List<Users> getUsers() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    public boolean deleteUser(int id) {
        if (isUser(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;

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
