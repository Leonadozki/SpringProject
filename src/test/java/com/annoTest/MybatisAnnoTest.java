package com.annoTest;

import com.annoDemo.dao.IAccountDao;
import com.annoDemo.dao.IUserDao;
import com.annoDemo.domain.Account;
import com.annoDemo.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *  测试基于注解的mybatis操作
 */
public class MybatisAnnoTest {

    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;
    private IAccountDao accountDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
        accountDao = session.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    /**
     *  注解实现保存user
     */
    @Test
    public void testAnnotationSave(){
        User user = new User();
        user.setGender("男");
        user.setUsername("热血男儿拿破仑");
        userDao.saveUser(user);
    }

    /**
     *  通过ID查询用户
     */
    @Test
    public void testAnnotationGetById(){
        User user = userDao.getById(48);
        System.out.println(user);
    }

    /**
     *  注解查询账户-用户信息（一对一）
     */
    @Test
    public void testAnnotationListAccountMap(){
        List<Account> accounts = accountDao.listAccountUser();
        for (Account account : accounts){
            System.out.println("---------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     *  查询用户及其所有对应账户
     */
    @Test
    public void testAnnotationListUserMap(){
        List<User> users = userDao.listAllUserAccount();
        for (User user: users){
            System.out.println("--------------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

}
