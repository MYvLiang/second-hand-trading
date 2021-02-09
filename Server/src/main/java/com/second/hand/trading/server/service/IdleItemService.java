package com.second.hand.trading.server.service;

import com.second.hand.trading.server.model.IdleItemModel;
import com.second.hand.trading.server.vo.PageVo;

import java.util.List;

/**
 * @author myl
 * @create 2020-12-23  17:46
 */
public interface IdleItemService {

    /**
     * 发布闲置
     * @param idleItemModel
     * @return
     */
    boolean addIdleItem(IdleItemModel idleItemModel);

    /**
     * 获取某个闲置的信息
     * @param id
     * @return
     */
    IdleItemModel getIdleItem(Long id);

    /**
     * 获取某个用户的所有闲置信息
     * @param userId
     * @return
     */
    List<IdleItemModel> getAllIdelItem(Long userId);

    /**
     * 搜索闲置
     * @param findValue
     * @param page
     * @param nums
     * @return
     */
    PageVo<IdleItemModel> findIdleItem(String findValue, int page, int nums);

    /**
     * 按分类获取闲置，分页器
     * @param idleLabel
     * @param page
     * @param nums
     * @return
     */
    PageVo<IdleItemModel> findIdleItemByLable(int idleLabel, int page, int nums);

    /**
     * 更新闲置的状态信息
     * @param idleItemModel
     * @return
     */
    boolean updateIdleItem(IdleItemModel idleItemModel);

    PageVo<IdleItemModel> adminGetIdleList(int status, int page, int nums) ;
}
