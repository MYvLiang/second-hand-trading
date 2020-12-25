package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.dao.IdleItemDao;
import com.second.hand.trading.server.dao.UserDao;
import com.second.hand.trading.server.model.IdleItemModel;
import com.second.hand.trading.server.model.UserModel;
import com.second.hand.trading.server.service.IdleItemService;
import com.second.hand.trading.server.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author myl
 * @create 2020-12-23  17:46
 */
@Service
public class IdleItemServiceImpl implements IdleItemService {

    @Resource
    private IdleItemDao idleItemDao;

    @Resource
    private UserDao userDao;

    public boolean addIdleItem(IdleItemModel idleItemModel) {
        return idleItemDao.insert(idleItemModel) == 1;
    }

    public IdleItemModel getIdleItem(Long id) {
        IdleItemModel idleItemModel=idleItemDao.selectByPrimaryKey(id);
        idleItemModel.setUser(userDao.selectByPrimaryKey(idleItemModel.getUserId()));
        return idleItemModel;
    }

    public List<IdleItemModel> getAllIdelItem(Long userId) {
        return idleItemDao.getAllIdleItem(userId);
    }

    public PageVo<IdleItemModel> findIdleItem(String findValue, int page, int nums) {
        List<IdleItemModel> list=idleItemDao.findIdleItem(findValue, (page - 1) * nums, nums);
        if(list.size()>0){
            List<Long> idList=new ArrayList<>();
            for(IdleItemModel i:list){
                idList.add(i.getUserId());
            }
            List<UserModel> userList=userDao.findUserByList(idList);
            Map<Long,UserModel> map=new HashMap<>();
            for(UserModel user:userList){
                map.put(user.getId(),user);
            }
            for(IdleItemModel i:list){
                i.setUser(map.get(i.getUserId()));
            }
        }
        int count=idleItemDao.countIdleItem(findValue);
        return new PageVo<>(list,count);
    }

    public PageVo<IdleItemModel> findIdleItemByLable(int idleLabel, int page, int nums) {
        List<IdleItemModel> list=idleItemDao.findIdleItemByLable(idleLabel, (page - 1) * nums, nums);
        if(list.size()>0){
            List<Long> idList=new ArrayList<>();
            for(IdleItemModel i:list){
                idList.add(i.getUserId());
            }
            List<UserModel> userList=userDao.findUserByList(idList);
            Map<Long,UserModel> map=new HashMap<>();
            for(UserModel user:userList){
                map.put(user.getId(),user);
            }
            for(IdleItemModel i:list){
                i.setUser(map.get(i.getUserId()));
            }
        }
        int count=idleItemDao.countIdleItemByLable(idleLabel);
        return new PageVo<>(list,count);
    }

    public boolean updateIdleItem(IdleItemModel idleItemModel){
        return idleItemDao.updateByPrimaryKeySelective(idleItemModel)==1;
    }
}
