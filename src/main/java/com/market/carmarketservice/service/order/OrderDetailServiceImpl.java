package com.market.carmarketservice.service.order;

import com.market.carmarketservice.dto.UserDTO;
import com.market.carmarketservice.model.order.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAllOrderDetail() {
        return (List<OrderDetail>) orderDetailRepository.findAll();
    }

    @Override
    public OrderDetailResponse getOrderDetail(int orderID) {
        try {
            List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailsByOrder_Id(orderID);
            OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
            UserDTO user = new UserDTO(orderDetails.get(0).getOrder().getUser());
            List<ProductInfo> products = new ArrayList<>();
            int total = 0;
            for (OrderDetail p : orderDetails) {
                products.add(new ProductInfo(p.getProduct(), p.getQuantity()));
                total += (p.getQuantity() * p.getProduct().getPrice());
            }
            orderDetailResponse.setId(orderID);
            orderDetailResponse.setUser(user);
            orderDetailResponse.setProducts(products);
            orderDetailResponse.setTotal(total);
            return orderDetailResponse;
        } catch (Exception e) {
            return null;
        }
    }
}
