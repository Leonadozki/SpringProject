package com.annoDemo.utils;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.sql.SQLException;


@Component("transactionManager")
public class TransactionManager {

    /**
     * 事务管理相关工具类，用于：开启事务、提交事务、回滚事务和释放连接
     * */

    @Resource
    private ConnectionUtils connectionUtils;

    /**
     * 开启事务
     * */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     * */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     * */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     * */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close(); // 还回连接池中
            connectionUtils.removeConnection();    // 和线程解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
