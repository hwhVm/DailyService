package com.beini.mapperslave;

import com.beini.bean.UserBean;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by beini on 2017/11/6.
 */
public interface UserSlaveMapper {
//    @Insert("insert into userbean(username,password,email,sex) values(#{username},#{password},#{email},#{sex})")
//    int insertUser(UserBean userBean);

    @Insert("insert into userbean(username,password,email,sex) values(#{username},#{password},#{email},#{sex})")
    int insertUser(UserBean userBean);

}
