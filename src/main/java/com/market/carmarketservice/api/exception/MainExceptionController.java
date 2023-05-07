package com.market.carmarketservice.api.exception;

import com.market.carmarketservice.exception.LinkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainExceptionController {

    @ExceptionHandler(value = LinkNotFoundException.class)
    public ResponseEntity<Object> linkNotFound(LinkNotFoundException exception) {
        return new ResponseEntity<>("Link not found", HttpStatus.NOT_FOUND);
    }
}
