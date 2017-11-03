package com.beini.service;

import com.beini.bean.UserBean;

import java.util.List;

/**
 * Created by beini on 2017/10/23.
 */
public interface UserService {

    UserBean findUserByEmail(String userName);

    UserBean findUserById(long userId);

    List<UserBean> queryUserByUserEmailAndPasswrod(String userName, String password);

    int registerUser(UserBean userBean);
}
