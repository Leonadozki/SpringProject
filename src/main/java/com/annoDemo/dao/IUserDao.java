package com.annoDemo.dao;

import com.annoDemo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *  用户的持久层接口
 * */
public interface IUserDao {

    /**
     *  查询所有用户方法
     * */
    @Select("select * from user")
    List<User> listAll();

    /**
     *  保存用户方法
     * */
    @Insert("insert into user(username,birthday,sex,address) values(#{username},#{birthday},#{sex},#{address})")
    void saveUser(User user);
}
