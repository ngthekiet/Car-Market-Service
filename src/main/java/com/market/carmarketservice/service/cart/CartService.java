package com.market.carmarketservice.service.cart;

import com.market.carmarketservice.request.cart.CartRequest;
import com.market.carmarketservice.request.cart.UpdateCartRequest;
import com.market.carmarketservice.response.cart.CartResponse;

public interface CartService {

    public CartResponse getCart(int uid);

    public boolean addToCart(CartRequest cartRequest);

    public boolean removeFromCart(int id);

    public boolean updateCart(UpdateCartRequest updateCartRequest);
}
