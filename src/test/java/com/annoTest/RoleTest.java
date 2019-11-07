package com.annoTest;

import com.annoDemo.dao.IRoleDao;
import com.annoDemo.dao.IUserDao;
import com.annoDemo.domain.Role;
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

public class RoleTest {

    private InputStream in;
    private SqlSession session;
    private IRoleDao roleDao;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        roleDao = session.getMapper(IRoleDao.class);
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.close();
        in.close();
    }

    /**
     *  返回role表所有数据
     */
    @Test
    public void testListRoles(){
        List<Role> roles = roleDao.listRoles();
        for (Role role : roles){
            System.out.println("-------------");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

    /**
     *  用户--权限多对多查询
     */
    @Test
    public void testListUserRoles(){
        List<User> users = userDao.listUserRole();
        for (User user : users){
            System.out.println("-------------");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }
}
