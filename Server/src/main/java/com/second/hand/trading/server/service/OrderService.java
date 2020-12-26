package com.second.hand.trading.server.service;

import com.second.hand.trading.server.model.OrderModel;

import java.util.List;

/**
 * @author myl
 * @create 2020-12-25  14:06
 */
public interface OrderService {

    boolean addOrder(OrderModel orderModel);

    OrderModel getOrder(Long id);

    boolean updateOrder(OrderModel orderModel);

    List<OrderModel> getMyOrder(Long userId);

    List<OrderModel> getMySoldIdle(Long userId);
}
