package com.market.carmarketservice.api;

import com.market.carmarketservice.service.message.MessageService;
import com.market.carmarketservice.service.order.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/pri")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    private final MessageService messageService;

    @RequestMapping(value = "/orderDetail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOrderDetail(@PathVariable("id") int orderId) {
        if (orderDetailService.getOrderDetail(orderId) != null)
            return new ResponseEntity<>(orderDetailService.getOrderDetail(orderId), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.OK);
    }
}
