package com.annoDemo.utils;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionUtils {

    /**
     * 获取当前线程上的连接
     * */
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    @Resource
    private DataSource dataSource;

    public Connection getThreadConnection(){
        // 1.先从threadLocal获取连接
        Connection conn = tl.get();
        // 2.判断当前线程上是否有连接
        if(conn == null){
            // 3.从数据源上获取一个连接并存入ThreadLocal
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            tl.set(conn);
        }
        // 4.返回当前线程上的连接
        return conn;
    }

    /**
     * 释放连接
     * */
    public void removeConnection(){
        tl.remove();
    }
}
