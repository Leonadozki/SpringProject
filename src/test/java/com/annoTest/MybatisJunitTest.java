package com.annoTest;

import com.annoDemo.dao.IUserDao;
import com.annoDemo.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisJunitTest {

    // 定义全局变量让测试类也能用
    private InputStream in;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    /**
     *  初始化全局变量
     * */
    @Before
    public void init() throws Exception{
        in  = Resources.getResourceAsStream("SqlMapperConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    /**
     *  销毁方法释放资源
     * */
    @After
    public void destroy() throws Exception{
        // 如果真的需要改变数据，使用提交事务方法: session.commit(); ps:单元测试不建议产生数据污染
        session.close();
        in.close();
    }

    /**
     *  新增user方法
     * */
    @Test
    public void testSave(){

        User user = new User();
        user.setUsername("leo");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("南岸区弹子石");
        userDao.saveUser(user);
        List<User> users = userDao.listAll();
        for (User user1: users) {
            if (user1.getUsername().equals("leo")){
                System.out.println("save()执行成功");
            }
        }
    }

    /**
     *  查找所有user方法
     */
    @Test
    public void testListAll(){

        List<User> users = userDao.listAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

}
