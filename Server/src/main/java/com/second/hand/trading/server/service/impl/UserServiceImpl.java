package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.dao.UserDao;
import com.second.hand.trading.server.model.UserModel;
import com.second.hand.trading.server.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author myl
 * @create 2020-12-17  10:21
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public List<UserModel> getUserList(){
        return userDao.getUserList();
    }

    public UserModel getUser(Long id){
        return userDao.selectByPrimaryKey(id);
    }

    public UserModel userLogin(String accountNumber, String userPassword){
        return userDao.userLogin(accountNumber,userPassword);
    }

    public boolean userSignIn(UserModel userModel){
        int n=0;
        try {
            n=userDao.insert(userModel);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return n == 1;
    }

    public boolean updateUserInfo(UserModel userModel){
        return userDao.updateByPrimaryKeySelective(userModel)==1;
    }

    public boolean updatePassword(String newPassword, String oldPassword,Long id){
        return userDao.updatePassword(newPassword,oldPassword,id)==1;
    }
}
