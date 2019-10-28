package com.annoDemo.dao;

import com.annoDemo.domain.User;

import java.util.List;

/**
 *  用户的持久层接口
 * */
public interface IUserDao {

    /**
     *  查询所有用户方法
     * */
    List<User> findAll();
}
