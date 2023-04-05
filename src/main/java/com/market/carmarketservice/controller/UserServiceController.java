package com.market.carmarketservice.controller;

import com.market.carmarketservice.auth.AuthenticationRequest;
import com.market.carmarketservice.auth.AuthenticationResponse;
import com.market.carmarketservice.auth.RegisterRequest;
import com.market.carmarketservice.exception.AccessErrorException;
import com.market.carmarketservice.exception.UserNotfoundException;
import com.market.carmarketservice.service.message.MessageService;
import com.market.carmarketservice.service.user.AuthenticationService;
import com.market.carmarketservice.bean.user.User;
import com.market.carmarketservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3001/")
@RequiredArgsConstructor
public class UserServiceController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final MessageService message;

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
    ) {
        if (!userService.existUser(request.getUsername()))
            return ResponseEntity.ok(authenticationService.register(request));
        return new ResponseEntity<>(message.useIsExist(), HttpStatus.FOUND);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        if (userService.updateUser(user, id))
            return new ResponseEntity<>(message.updateUserSuccesses(), HttpStatus.OK);
        else
            throw new UserNotfoundException();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable("id") int id) {
        if (userService.getUser(id) != null)
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        else
            throw new UserNotfoundException();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        if (userService.deleteUser(id))
            return new ResponseEntity<>(message.deleteUserSuccesses(), HttpStatus.OK);
        else
            throw new UserNotfoundException();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            throw new AccessErrorException();
        }
    }
}
