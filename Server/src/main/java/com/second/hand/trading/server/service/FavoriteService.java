package com.second.hand.trading.server.service;

import com.second.hand.trading.server.model.FavoriteModel;

import java.util.List;

/**
 * @author myl
 * @create 2020-12-26  20:23
 */
public interface FavoriteService {

    boolean addFavorite(FavoriteModel favoriteModel);

    boolean deleteFavorite(Long id);

    Integer isFavorite(Long userId,Long idleId);

    List<FavoriteModel> getAllFavorite(Long userId);
}
