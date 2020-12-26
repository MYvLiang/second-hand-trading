package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.dao.FavoriteDao;
import com.second.hand.trading.server.dao.IdleItemDao;
import com.second.hand.trading.server.model.FavoriteModel;
import com.second.hand.trading.server.model.IdleItemModel;
import com.second.hand.trading.server.model.OrderModel;
import com.second.hand.trading.server.service.FavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author myl
 * @create 2020-12-26  20:23
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteDao favoriteDao;

    @Resource
    private IdleItemDao idleItemDao;

    public boolean addFavorite(FavoriteModel favoriteModel){
        int n=0;
        try {
            n=favoriteDao.insert(favoriteModel);
        }catch (Exception ignored){
        }
        return n==1;
    }

    public boolean deleteFavorite(Long id){
        return favoriteDao.deleteByPrimaryKey(id)==1;
    }

    public Integer isFavorite(Long userId,Long idleId){
        return favoriteDao.checkFavorite(userId,idleId);
    }

    public List<FavoriteModel> getAllFavorite(Long userId){
        List<FavoriteModel> list=favoriteDao.getMyFavorite(userId);
        if(list.size()>0){
            List<Long> idleIdList=new ArrayList<>();

        }
        if(list.size()>0){
            List<Long> idleIdList=new ArrayList<>();
            for(FavoriteModel i:list){
                idleIdList.add(i.getIdleId());
            }
            List<IdleItemModel> idleItemModelList=idleItemDao.findIdleByList(idleIdList);
            Map<Long,IdleItemModel> map=new HashMap<>();
            for(IdleItemModel idle:idleItemModelList){
                map.put(idle.getId(),idle);
            }
            for(FavoriteModel i:list){
                i.setIdleItem(map.get(i.getIdleId()));
            }
        }
        return list;
    }
}
