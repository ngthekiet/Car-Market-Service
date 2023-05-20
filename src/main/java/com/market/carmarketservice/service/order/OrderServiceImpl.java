package com.market.carmarketservice.service.order;

import com.market.carmarketservice.model.cart.Cart;
import com.market.carmarketservice.model.cart.CartRepository;
import com.market.carmarketservice.model.order.Order;
import com.market.carmarketservice.model.order.OrderDetail;
import com.market.carmarketservice.model.order.OrderDetailRepository;
import com.market.carmarketservice.model.order.OrderRepository;
import com.market.carmarketservice.model.user.User;
import com.market.carmarketservice.model.user.UserRepository;
import com.market.carmarketservice.response.order.OrderResponse;
import com.market.carmarketservice.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public boolean order(int uid) {
        try {
            List<Cart> carts = cartRepository.getCartsByUserId(uid);
            Optional<User> user = userRepository.findById(uid);
            var order = Order.builder()
                    .user(user.get())
                    .status("Chờ xác nhận")
                    .createDate(new Timestamp(System.currentTimeMillis()))
                    .updateDate(new Timestamp(System.currentTimeMillis()))
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<OrderResponse> getOrders(int uid) {
        List<OrderResponse> response = new ArrayList<>();
        List<Order> order = orderRepository.getOrderByUserId(uid);
        for (Order o : order) {
            OrderResponse or = new OrderResponse();
            or.setId(o.getId());
            or.setUser(new UserResponse(o.getUser()));
            or.setStatus(o.getStatus());
            or.setCreateDate(o.getCreateDate());
            or.setUpdateDate(o.getUpdateDate());
            response.add(or);
        }
        return response;
    }
}
