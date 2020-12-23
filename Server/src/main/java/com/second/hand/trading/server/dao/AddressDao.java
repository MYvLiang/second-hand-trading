package com.second.hand.trading.server.dao;

import com.second.hand.trading.server.model.AddressModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressDao {
    int deleteByPrimaryKeyAndUser(Long id,Long userId);

    int insert(AddressModel record);

    int insertSelective(AddressModel record);

    AddressModel selectByPrimaryKey(Long id);

    List<AddressModel> getAddressByUser(Long userId);

    List<AddressModel> getDefaultAddress(Long userId);

    int updateByPrimaryKeySelective(AddressModel record);

    int updateByUserIdSelective(AddressModel record);

    int updateByPrimaryKey(AddressModel record);
}