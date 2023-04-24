package com.market.carmarketservice.controller;

import com.market.carmarketservice.service.message.MessageService;
import com.market.carmarketservice.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MessageService messageService;

    @RequestMapping(value = "/order/{uid}", method = RequestMethod.POST)
    public ResponseEntity<Object> order(@PathVariable("uid") int uid) {
        if (orderService.order(uid))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/order/{uid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOrders(@PathVariable("uid") int uid) {
        if (orderService.getOrders(uid) != null)
            return new ResponseEntity<>(orderService.getOrders(uid), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }
}
