package com.market.carmarketservice.service.order;

import com.market.carmarketservice.response.order.OrderResponse;

import java.util.List;

public interface OrderService {
    public boolean order(int uid);

    public List<OrderResponse> getOrders(int uid);
}
