package com.annoDemo.dao;

import com.annoDemo.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


    /**
     *  修改用户方法
     */
    @Update("update user set username=#{username}, birthday=#{birthday}, sex=#{sex}, address=#{address} where id=#{id}")
    void updateUser(User user);


    /**
     *  删除用户方法
     */
    @Delete("delete from user where id=#{id}")
    void removeUser(Integer id);

    /**
     *  查询单个用户
     */
    @Select("select * from user where id=#{id}")
    User getById(Integer id);

    /**
     *  按名称模糊查询
     */
    @Select("select * from user where username like CONCAT('%', #{name}, '%') ")
    List<User> listByName(String name);
}
