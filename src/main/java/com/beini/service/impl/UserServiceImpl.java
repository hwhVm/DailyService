package com.beini.service.impl;

import com.beini.bean.UserBean;
import com.beini.mapper.DailyMapper;
import com.beini.mapper.UserMapper;
import com.beini.mapperslave.UserSlaveMapper;
import com.beini.service.UserService;
import com.beini.util.BLog;
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
    @Autowired
    UserSlaveMapper userSlaveMapper;

    public UserBean findUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    public UserBean findUserById(long userId) {
        return null;
    }

    public List<UserBean> queryUserByUserEmailAndPasswrod(String email, String password) {
        return userMapper.queryUserByUserEmailAndPasswrod(email, password);
    }

    public int registerUser(UserBean userBean) {
        BLog.d("    userBean.toString()="+userBean.toString());
        return userSlaveMapper.insertUser(userBean);
    }


}
