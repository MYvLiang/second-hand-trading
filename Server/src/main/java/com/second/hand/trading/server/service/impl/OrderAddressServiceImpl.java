package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.dao.OrderAddressDao;
import com.second.hand.trading.server.model.OrderAddressModel;
import com.second.hand.trading.server.service.OrderAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author myl
 * @create 2020-12-26  16:05
 */
@Service
public class OrderAddressServiceImpl implements OrderAddressService {

    @Resource
    private OrderAddressDao orderAddressDao;

    public boolean addOrderAddress(OrderAddressModel orderAddressModel){
        return orderAddressDao.insert(orderAddressModel)==1;
    }

    public boolean updateOrderAddress(OrderAddressModel orderAddressModel){
        orderAddressModel.setOrderId(null);
        return orderAddressDao.updateByPrimaryKeySelective(orderAddressModel)==1;
    }

    public OrderAddressModel getOrderAddress(Long orderId){
        return orderAddressDao.selectByOrderId(orderId);
    }
}
