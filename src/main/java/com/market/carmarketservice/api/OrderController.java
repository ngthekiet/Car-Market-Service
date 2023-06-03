package com.market.carmarketservice.api;

import com.market.carmarketservice.model.order.Order;
import com.market.carmarketservice.request.order.OrderIDRequest;
import com.market.carmarketservice.request.order.OrderRequest;
import com.market.carmarketservice.service.message.MessageService;
import com.market.carmarketservice.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/pri")
public class OrderController {
    private final OrderService orderService;
    private final MessageService messageService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Object> order(@RequestBody OrderRequest request) {
        if (orderService.order(request.getUid()))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllOrder() {
        if (orderService.getAllOrder() != null)
            return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/order/{uid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOrders(@PathVariable("uid") int uid) {
        if (orderService.getOrders(uid) != null)
            return new ResponseEntity<>(orderService.getOrders(uid), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/orderBy/{oid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOrder(@PathVariable("oid") int oid) {
        if (orderService.getOrders(oid) != null)
            return new ResponseEntity<>(orderService.getOrder(oid), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.PUT)
    public ResponseEntity<Object> cancelOrder(@RequestBody OrderIDRequest request) {
        return new ResponseEntity<>(orderService.cancelOrder(request.getOid()), HttpStatus.OK);
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateStatus(@PathVariable("id") int id, @RequestBody Order order) {
        if (orderService.updateStatus(id, order))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }
}
