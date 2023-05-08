package com.market.carmarketservice.service.cart;

import com.market.carmarketservice.model.cart.*;
import com.market.carmarketservice.model.product.Product;
import com.market.carmarketservice.model.product.ProductRepository;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.model.user.UserRepository;
import com.market.carmarketservice.request.cart.CartRequest;
import com.market.carmarketservice.response.cart.CartResponse;
import com.market.carmarketservice.response.cart.ProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponse getCart(int uid) {
        try {
            List<Cart> cartList = cartRepository.getCartsByUserId(uid);
            List<ProductInfo> productInfos = new ArrayList<>();
            for (Cart c : cartList) {
                productInfos.add(new ProductInfo(c.getId(), c.getProduct(), c.getQuantity()));
            }
            CartResponse cartResponse = new CartResponse();
            cartResponse.setId(cartList.get(0).getId());
            cartResponse.setLastName(cartList.get(0).getUser().getLastname());
            cartResponse.setProducts(productInfos);
            return cartResponse;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addToCart(CartRequest cartRequest) {
        try {
            List<Cart> cartList = cartRepository.getCartsByUserId(cartRequest.getUser());
            Map<Integer, Cart> mapCart = new HashMap<>();
            for (Cart c : cartList)
                mapCart.put(c.getProduct().getId(), c);
            if (mapCart.containsKey(cartRequest.getProduct())) {
                updateCart(cartRequest, mapCart);
                return true;
            }
            addCart(cartRequest);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeFromCart(int id) {
        try {
            cartRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void addCart(CartRequest cartRequest) {
        Optional<User> optionalUser = userRepository.findById(cartRequest.getUser());
        Optional<Product> optionalProduct = productRepository.findById(cartRequest.getProduct());
        var cart = Cart.builder()
                .user(optionalUser.get())
                .product(optionalProduct.get())
                .quantity(cartRequest.getQuantity())
                .build();
        cartRepository.save(cart);
    }

    public void updateCart(CartRequest cartRequest, Map<Integer, Cart> mapCart) {
        Cart cartOld = mapCart.get(cartRequest.getProduct());
        var cart = Cart.builder()
                .id(cartOld.getId())
                .user(cartOld.getUser())
                .product(cartOld.getProduct())
                .quantity(cartOld.getQuantity() + cartRequest.getQuantity())
                .build();
        cartRepository.save(cart);
    }
}
