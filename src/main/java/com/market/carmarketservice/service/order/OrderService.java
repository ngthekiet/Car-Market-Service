package com.market.carmarketservice.service.order;

import com.market.carmarketservice.model.order.Order;

import java.util.List;

public interface OrderService {
    public boolean order(int uid);

    public List<Order> getOrders(int uid);
}
