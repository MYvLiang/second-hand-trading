package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.dao.IdleItemDao;
import com.second.hand.trading.server.dao.OrderDao;
import com.second.hand.trading.server.model.IdleItemModel;
import com.second.hand.trading.server.model.OrderModel;
import com.second.hand.trading.server.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author myl
 * @create 2020-12-25  14:07
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private IdleItemDao idleItemDao;

    @Transactional
    public boolean addOrder(OrderModel orderModel){
        IdleItemModel idleItemModel=idleItemDao.selectByPrimaryKey(orderModel.getIdleId());
        System.out.println(idleItemModel.getIdleStatus());
        if(idleItemModel.getIdleStatus()!=1){
            return false;
        }
        IdleItemModel idleItem=new IdleItemModel();
        idleItem.setId(orderModel.getIdleId());
        idleItem.setUserId(idleItemModel.getUserId());
        idleItem.setIdleStatus((byte)2);
        return orderDao.insert(orderModel)==1&&
                idleItemDao.updateByPrimaryKeySelective(idleItem)==1;
    }

    public OrderModel getOrder(Long id){
        OrderModel orderModel=orderDao.selectByPrimaryKey(id);
        orderModel.setIdleItem(idleItemDao.selectByPrimaryKey(orderModel.getIdleId()));
        return orderModel;
    }

    public boolean updateOrder(OrderModel orderModel){
        //不可修改的信息
        orderModel.setOrderNumber(null);
        orderModel.setUserId(null);
        orderModel.setIdleId(null);
        orderModel.setCreateTime(null);
        return orderDao.updateByPrimaryKeySelective(orderModel)==1;
    }

    public List<OrderModel> getMyOrder(Long userId){
        List<OrderModel> list=orderDao.getMyOrder(userId);
        if(list.size()>0){
            List<Long> idleIdList=new ArrayList<>();
            for(OrderModel i:list){
                idleIdList.add(i.getIdleId());
            }
            List<IdleItemModel> idleItemModelList=idleItemDao.findIdleByList(idleIdList);
            Map<Long,IdleItemModel> map=new HashMap<>();
            for(IdleItemModel idle:idleItemModelList){
                map.put(idle.getId(),idle);
            }
            for(OrderModel i:list){
                i.setIdleItem(map.get(i.getIdleId()));
            }
        }
        return list;
    }

    public List<OrderModel> getMySoldIdle(Long userId){
        List<IdleItemModel> list=idleItemDao.getAllIdleItem(userId);
        List<OrderModel> orderList=null;
        if(list.size()>0){
            List<Long> idleIdList=new ArrayList<>();
            for(IdleItemModel i:list){
                idleIdList.add(i.getId());
            }
            orderList=orderDao.findOrderByIdleIdList(idleIdList);
            Map<Long,IdleItemModel> map=new HashMap<>();
            for(IdleItemModel idle:list){
                map.put(idle.getId(),idle);
            }
            for(OrderModel o:orderList){
                o.setIdleItem(map.get(o.getIdleId()));
            }
        }
        return orderList;
    }
}
