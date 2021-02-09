package com.second.hand.trading.server.dao;

import com.second.hand.trading.server.model.AdminModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminDao {
    int deleteByPrimaryKey(Long id);

    int insert(AdminModel record);

    int insertSelective(AdminModel record);

    AdminModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminModel record);

    int updateByPrimaryKey(AdminModel record);

    AdminModel login(@Param("accountNumber") String accountNumber, @Param("adminPassword") String adminPassword);

    List<AdminModel> getList(int begin, int nums);

    int getCount();
}