package com.second.hand.trading.server.service;

import com.second.hand.trading.server.model.OrderAddressModel;

/**
 * @author myl
 * @create 2020-12-26  16:04
 */
public interface OrderAddressService {

    boolean addOrderAddress(OrderAddressModel orderAddressModel);

    boolean updateOrderAddress(OrderAddressModel orderAddressModel);

    OrderAddressModel getOrderAddress(Long orderId);
}
