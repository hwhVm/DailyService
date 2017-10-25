package com.beini.service.impl;

import com.beini.bean.UserBean;
import com.beini.mapper.DailyMapper;
import com.beini.mapper.UserMapper;
import com.beini.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by beini on 2017/10/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean findUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public UserBean findUserById(long userId) {
        return null;
    }

    @Override
    public List<UserBean> queryUserByUserEmailAndPasswrod(String email, String password) {
        return userMapper.queryUserByUserEmailAndPasswrod(email, password);
    }

    @Override
    public int registerUser(UserBean userBean) {
        return userMapper.insertUser(userBean);
    }


}
