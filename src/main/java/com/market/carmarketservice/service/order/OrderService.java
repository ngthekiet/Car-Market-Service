package com.market.carmarketservice.service.order;

import com.market.carmarketservice.response.order.OrderResponse;

import java.util.List;

public interface OrderService {
    boolean order(int uid);

    List<OrderResponse> getOrders(int uid);

    boolean cancelOrder(int id);

    OrderResponse getOrder(int oid);
}
