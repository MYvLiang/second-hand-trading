package com.second.hand.trading.server.service;

import com.second.hand.trading.server.model.FavoriteModel;

import java.util.List;

/**
 * @author myl
 * @create 2020-12-26  20:23
 */
public interface FavoriteService {

    /**
     * 添加收藏
     * @param favoriteModel
     * @return
     */
    boolean addFavorite(FavoriteModel favoriteModel);

    /**
     * 取消收藏
     * @param id
     * @return
     */
    boolean deleteFavorite(Long id);

    /**
     * 判断是否收藏
     * @param userId
     * @param idleId
     * @return
     */
    Integer isFavorite(Long userId,Long idleId);

    /**
     * 获取收藏列表
     * @param userId
     * @return
     */
    List<FavoriteModel> getAllFavorite(Long userId);
}
