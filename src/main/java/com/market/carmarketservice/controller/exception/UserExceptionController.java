package com.market.carmarketservice.controller.exception;

import com.market.carmarketservice.exception.LinkNotFoundException;
import com.market.carmarketservice.exception.UserIsExistException;
import com.market.carmarketservice.exception.UserNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = UserNotfoundException.class)
    public ResponseEntity<Object> userNotfound(UserNotfoundException exception) {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserIsExistException.class)
    public ResponseEntity<Object> userIsExist(UserIsExistException exception) {
        return new ResponseEntity<>("User is exist", HttpStatus.NOT_FOUND);
    }
}
