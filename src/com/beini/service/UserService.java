package com.beini.service;

import com.beini.bean.UserBean;

import java.util.List;

/**
 * Created by beini on 2017/10/23.
 */
public interface UserService {

    UserBean findUserByName(String userName);

    UserBean findUserById(long userId);

    List<UserBean> queryUserByUserNameAndPasswrod(String userName, String password);

}
