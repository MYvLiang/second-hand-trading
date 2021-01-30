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

    /**
     * 为订单新增地址信息
     * @param orderAddressModel
     * @return
     */
    public boolean addOrderAddress(OrderAddressModel orderAddressModel){
        return orderAddressDao.insert(orderAddressModel)==1;
    }

    /**
     * 更新订单的地址信息，未验证用户身份
     * @param orderAddressModel
     * @return
     */
    public boolean updateOrderAddress(OrderAddressModel orderAddressModel){
        orderAddressModel.setOrderId(null);
        return orderAddressDao.updateByPrimaryKeySelective(orderAddressModel)==1;
    }

    /**
     * 获取订单的地址信息
     * orderId建索引
     * @param orderId
     * @return
     */
    public OrderAddressModel getOrderAddress(Long orderId){
        return orderAddressDao.selectByOrderId(orderId);
    }
}
