package com.market.carmarketservice.service.user;

import com.market.carmarketservice.request.auth.AuthenticationRequest;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean updateUser(User user, int id) {
        Optional<User> userOld = userRepository.findById(id);
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
    public User getUser(int id) {
        if (isUser(id)) {
            Optional<User> optional = userRepository.findById(id);
            User user = optional.get();
            return user;
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
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

    @Override
    public boolean validPassword(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByUsername(request.getUsername());
            if (user != null)
                return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
