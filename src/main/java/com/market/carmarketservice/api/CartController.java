package com.market.carmarketservice.api;

import com.market.carmarketservice.request.cart.CartRequest;
import com.market.carmarketservice.request.cart.UpdateCartRequest;
import com.market.carmarketservice.service.cart.CartService;
import com.market.carmarketservice.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/pri")
public class CartController {
    private final CartService cartService;
    private final MessageService messageService;

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public ResponseEntity<Object> addToCart(@RequestBody CartRequest cart) {
        if (cartService.addToCart(cart))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/removeFromCart/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeFromCart(@PathVariable("id") int id) {
        if (cartService.removeFromCart(id))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getCart/{uid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCart(@PathVariable("uid") int uid) {
        return new ResponseEntity<>(cartService.getCart(uid), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCart", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCart(@RequestBody UpdateCartRequest updateCartRequest) {
        return new ResponseEntity<>(cartService.updateCart(updateCartRequest), HttpStatus.OK);
    }
}
