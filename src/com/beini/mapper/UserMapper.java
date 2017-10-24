package com.beini.mapper;

import com.beini.bean.DailyBean;
import com.beini.bean.UserBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by beini on 2017/10/23.
 */
public interface UserMapper {

    @Update("UPDATE User set username=#{username},password=#{password},email=#{email},sex=#{sex} where id=#{id}")
    void updateUser(UserBean user);

    @Insert("insert into User(username,password,email,sex) values(#{username},#{password},#{email},#{sex});")
    void insertUser(UserBean user);

    @Delete("DELETE FROM User where id=#{id}")
    int deleteUserById(int id);

    @Select("select id id,username username,password password,email email,sex sex from User where id=#{id}")
    UserBean getUserById(int id);

    @Select("select * from  User limit #{start} ,  #{num} ")
    List<UserBean> queryUserInfo(@Param("start") int start, @Param("num") int num);

    @Select("select count(*) from User")
    int getUserCount();

    @Select("select * from User where username=#{username} and  password=#{password}")
    List<UserBean> queryUserByUserNameAndPasswrod(@Param("username") String name, @Param("password") String password);

    @Select("select id id,username username,password password,email email,sex sex from User where username=#{username}")
    UserBean getUserByName(String username);

}
