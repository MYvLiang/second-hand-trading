package com.second.hand.trading.server.service;

import com.second.hand.trading.server.model.IdleItemModel;
import com.second.hand.trading.server.vo.PageVo;

import java.util.List;

/**
 * @author myl
 * @create 2020-12-23  17:46
 */
public interface IdleItemService {

    boolean addIdleItem(IdleItemModel idleItemModel);

    IdleItemModel getIdleItem(Long id);

    List<IdleItemModel> getAllIdelItem(Long userId);

    PageVo<IdleItemModel> findIdleItem(String findValue, int page, int nums);

    PageVo<IdleItemModel> findIdleItemByLable(int idleLabel, int page, int nums);

    boolean updateIdleItem(IdleItemModel idleItemModel);
}
