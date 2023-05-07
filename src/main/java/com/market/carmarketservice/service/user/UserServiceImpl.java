package com.market.carmarketservice.service.user;

import com.market.carmarketservice.request.auth.AuthenticationRequest;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.model.user.UserRepository;
import com.market.carmarketservice.request.user.UserRequest;
import com.market.carmarketservice.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public boolean updateUser(User user, int id) {
        Optional<User> userOld = userRepository.findById(id);
        if (isUser(id)) {
            if (user.getAvatar() == null)
                user.setAvatar(userOld.get().getAvatar());
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
    public UserResponse getUser(int id) {
        if (isUser(id)) {
            Optional<User> optional = userRepository.findById(id);
            UserResponse user = new UserResponse(optional.get());
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

    @Override
    public boolean changeAvatar(UserRequest avatar, int id) {
        Optional<User> userOld = userRepository.findById(id);
        if (isUser(id)) {
            User user = userOld.get();
            user.setAvatar(avatar.getAvatar());
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
