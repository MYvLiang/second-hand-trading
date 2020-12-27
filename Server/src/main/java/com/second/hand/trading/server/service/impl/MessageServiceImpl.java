package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.dao.IdleItemDao;
import com.second.hand.trading.server.dao.MessageDao;
import com.second.hand.trading.server.dao.UserDao;
import com.second.hand.trading.server.model.IdleItemModel;
import com.second.hand.trading.server.model.MessageModel;
import com.second.hand.trading.server.model.UserModel;
import com.second.hand.trading.server.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author myl
 * @create 2020-12-26  22:37
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageDao messageDao;

    @Resource
    private UserDao userDao;

    @Resource
    private IdleItemDao idleItemDao;

    public boolean addMessage(MessageModel messageModel){
        return messageDao.insert(messageModel)==1;
    }

    public boolean deleteMessage(Long id){
        return messageDao.deleteByPrimaryKey(id)==1;
    }

    public MessageModel getMessage(Long id){
        return messageDao.selectByPrimaryKey(id);
    }

    public List<MessageModel> getAllMyMessage(Long userId){
        List<MessageModel> list=messageDao.getMyMessage(userId);
        if(list.size()>0){
            List<Long> idList=new ArrayList<>();
            for(MessageModel i:list){
                idList.add(i.getUserId());
            }
            List<UserModel> userList=userDao.findUserByList(idList);
            Map<Long,UserModel> map=new HashMap<>();
            for(UserModel user:userList){
                map.put(user.getId(),user);
            }
            for(MessageModel i:list){
                i.setFromU(map.get(i.getUserId()));
            }

            List<Long> idleIdList=new ArrayList<>();
            for(MessageModel i:list){
                idleIdList.add(i.getIdleId());
            }
            List<IdleItemModel> idleList=idleItemDao.findIdleByList(idleIdList);
            Map<Long,IdleItemModel> idleMap=new HashMap<>();
            for(IdleItemModel idle:idleList){
                idleMap.put(idle.getId(),idle);
            }
            for(MessageModel i:list){
                i.setIdle(idleMap.get(i.getIdleId()));
            }
        }
        return list;
    }

    public List<MessageModel> getAllIdleMessage(Long idleId){
        List<MessageModel> list=messageDao.getIdleMessage(idleId);
        if(list.size()>0){
            List<Long> idList=new ArrayList<>();
            for(MessageModel i:list){
                idList.add(i.getUserId());
            }
            List<UserModel> userList=userDao.findUserByList(idList);
            Map<Long,UserModel> map=new HashMap<>();
            for(UserModel user:userList){
                map.put(user.getId(),user);
            }
            for(MessageModel i:list){
                i.setFromU(map.get(i.getUserId()));
            }
            Map<Long,MessageModel> mesMap=new HashMap<>();
            for(MessageModel i:list){
                mesMap.put(i.getId(),i);
            }
            for(MessageModel i:list){
                MessageModel toM=new MessageModel();
                UserModel toU=new UserModel();
                if(i.getToMessage()!=null){
                    toM.setContent(mesMap.get(i.getToMessage()).getContent());
                    toU.setNickname(map.get(i.getToUser()).getNickname());
                }
                i.setToM(toM);
                i.setToU(toU);
            }
        }
        return list;
    }
}
