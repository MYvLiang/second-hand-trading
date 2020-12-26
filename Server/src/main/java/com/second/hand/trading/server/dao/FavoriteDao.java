package com.second.hand.trading.server.dao;

import com.second.hand.trading.server.model.FavoriteModel;

public interface FavoriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(FavoriteModel record);

    int insertSelective(FavoriteModel record);

    FavoriteModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FavoriteModel record);

    int updateByPrimaryKey(FavoriteModel record);
}