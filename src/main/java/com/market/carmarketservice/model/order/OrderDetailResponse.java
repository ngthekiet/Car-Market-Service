package com.market.carmarketservice.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    private Integer id;
    private UserInfo user;
    private List<ProductInfo> products;
    private Integer total;
}
