package com.second.hand.trading.server.service;

import com.second.hand.trading.server.model.UserModel;

import java.util.List;

/**
 * @author myl
 * @create 2020-12-17  10:15
 */
public interface UserService {

    List<UserModel> getUserList();

    UserModel getUser(Long id);

    UserModel userLogin(String accountNumber, String userPassword);
}
