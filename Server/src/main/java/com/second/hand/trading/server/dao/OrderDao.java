package com.second.hand.trading.server.dao;

import com.second.hand.trading.server.model.OrderModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderModel record);

    int insertSelective(OrderModel record);

    OrderModel selectByPrimaryKey(Long id);

    List<OrderModel> getMyOrder(Long userId);

    List<OrderModel> getAllOrder(int begin, int nums);

    int countAllOrder();

    List<OrderModel> findOrderByIdleIdList(List<Long> idleIdList);

    int updateByPrimaryKeySelective(OrderModel record);

    int updateByPrimaryKey(OrderModel record);
}