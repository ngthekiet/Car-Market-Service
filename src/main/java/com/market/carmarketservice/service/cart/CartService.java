package com.market.carmarketservice.service.cart;

import com.market.carmarketservice.model.cart.CartRequest;
import com.market.carmarketservice.model.cart.CartResponse;

public interface CartService {

    public CartResponse getCart(int uid);

    public boolean addToCart(CartRequest cartRequest);

    public boolean removeFromCart(int id);
}
