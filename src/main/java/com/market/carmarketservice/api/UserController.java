package com.market.carmarketservice.api;

import com.market.carmarketservice.request.auth.AuthenticationRequest;
import com.market.carmarketservice.response.auth.AuthenticationResponse;
import com.market.carmarketservice.request.auth.RegisterRequest;
import com.market.carmarketservice.request.valid.Username;
import com.market.carmarketservice.service.message.MessageService;
import com.market.carmarketservice.service.user.AuthenticationService;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3001/")
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final MessageService messageService;

    @RequestMapping(value = "/pri/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(messageService.notFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/pri/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable("id") int id) {
        if (userService.getUser(id) != null)
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        return new ResponseEntity<>(messageService.notFound(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/pub/register")
    public ResponseEntity<Object> register(
            @RequestBody RegisterRequest request
    ) {
        if (!userService.existUser(request.getUsername()))
            return ResponseEntity.ok(authenticationService.register(request));
        return new ResponseEntity<>(messageService.userIsExist(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/pub/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @RequestMapping(value = "/pri/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        if (userService.updateUser(user, id))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/pri/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        if (userService.deleteUser(id))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pub/checkUsername", method = RequestMethod.POST)
    public Boolean checkUsername(@RequestBody Username username) {
        return userService.existUser(username.getUsername());
    }

    @RequestMapping(value = "/pub/checkPassword", method = RequestMethod.POST)
    public Boolean checkPassword(@RequestBody AuthenticationRequest request) {
        return userService.validPassword(request);
    }

}
