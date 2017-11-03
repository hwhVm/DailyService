package com.beini.mapper;

import com.beini.bean.UserBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by beini on 2017/10/23.
 */
public interface UserMapper {

    @Insert("insert into userbean(username,password,email,sex) values(#{username},#{password},#{email},#{sex})")
    int insertUser(UserBean userBean);

    @Select("select * from userbean where email=#{email} and  password=#{password}")
    List<UserBean> queryUserByUserEmailAndPasswrod(@Param("email") String email, @Param("password") String password);

    @Select("select user_id user_id,username username,password password,email email,sex sex from userbean where email=#{email}")
    UserBean getUserByEmail(String email);


    @Update("UPDATE User set username=#{username},password=#{password},email=#{email},sex=#{sex} where user_id=#{user_id}")
    void updateUser(UserBean user);

    @Delete("DELETE FROM User where user_id=#{user_id}")
    int deleteUserById(int user_id);

    @Select("select user_id user_id,username username,password password,email email,sex sex from User where user_id=#{user_id}")
    UserBean getUserById(int user_id);

    @Select("select * from  User limit #{start} ,  #{num} ")
    List<UserBean> queryUserInfo(@Param("start") int start, @Param("num") int num);

    @Select("select count(*) from User")
    int getUserCount();

}
