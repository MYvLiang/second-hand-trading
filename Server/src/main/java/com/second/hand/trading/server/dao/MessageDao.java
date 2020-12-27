package com.second.hand.trading.server.dao;

import com.second.hand.trading.server.model.MessageModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(MessageModel record);

    int insertSelective(MessageModel record);

    MessageModel selectByPrimaryKey(Long id);

    List<MessageModel> getMyMessage(Long userId);

    List<MessageModel> getIdleMessage(Long idleId);

    int updateByPrimaryKeySelective(MessageModel record);

    int updateByPrimaryKey(MessageModel record);
}