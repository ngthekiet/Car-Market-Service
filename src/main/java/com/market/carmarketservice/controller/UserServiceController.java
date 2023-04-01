package com.market.carmarketservice.controller;

import com.market.carmarketservice.auth.AuthenticationRequest;
import com.market.carmarketservice.auth.AuthenticationResponse;
import com.market.carmarketservice.auth.RegisterRequest;
import com.market.carmarketservice.exception.UserNotfoundException;
import com.market.carmarketservice.service.user.AuthenticationService;
import com.market.carmarketservice.bean.user.User;
import com.market.carmarketservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserServiceController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
    ) {
        boolean isUserExist = userService.existUser(request.getUsername());
        if (!isUserExist)
            return ResponseEntity.ok(authenticationService.register(request));
        return new ResponseEntity<>("User is exist", HttpStatus.FOUND);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        boolean isUserExist = userService.isUser(id);
        if (isUserExist) {
            user.setId(id);
            userService.updateUser(user);
            return new ResponseEntity<>("User is updated successfully", HttpStatus.OK);
        } else {
            throw new UserNotfoundException();
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable("id") int id) {
        boolean isUserExist = userService.isUser(id);
        if (isUserExist) {
            User user = userService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            throw new UserNotfoundException();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        boolean isUserExist = userService.isUser(id);
        if (isUserExist) {
            userService.deleteUser(id);
            return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
        } else {
            throw new UserNotfoundException();
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        List<User> userList = userService.getUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
