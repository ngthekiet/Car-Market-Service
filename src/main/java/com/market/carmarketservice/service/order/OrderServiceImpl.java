package com.market.carmarketservice.service.order;

import com.market.carmarketservice.model.cart.Cart;
import com.market.carmarketservice.model.cart.CartRepository;
import com.market.carmarketservice.model.order.Order;
import com.market.carmarketservice.model.order.OrderDetail;
import com.market.carmarketservice.model.order.OrderDetailRepository;
import com.market.carmarketservice.model.order.OrderRepository;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public boolean order(int uid) {
        List<Cart> carts = cartRepository.getCartsByUserId(uid);
        Optional<User> user = userRepository.findById(uid);
        var order = Order.builder()
                .user(user.get())
                .status("Chờ xác nhận")
                .build();
        orderRepository.save(order);
        for (Cart c : carts) {
            Optional<Order> optional = orderRepository.findById(order.getId());
            var orderDetail = OrderDetail.builder()
                    .order(optional.get())
                    .product(c.getProduct())
                    .price(1000)
                    .quantity(c.getQuantity())
                    .build();
            orderDetailRepository.save(orderDetail);
            cartRepository.deleteById(c.getId());
        }
        return false;
    }

    @Override
    public List<Order> getOrders(int uid) {
        return orderRepository.getOrderByUserId(uid);
    }
}
