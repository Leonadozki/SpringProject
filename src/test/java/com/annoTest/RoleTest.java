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
    SqlSessionFactory factory;
    private IRoleDao roleDao;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
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
     *  用户--权限多对多查询，延迟加载改造
     */
    @Test
    public void testListUserRoles(){
        List<User> users = userDao.listUserRole();
        for (User user : users){
            System.out.println("-------------");
            System.out.println(user);
//            System.out.println(user.getRoles());
        }
    }

    /**
     *  测试二级缓存
     *  注：开启后，二级缓存还会将对象写到硬盘（IO操作）在需要时读取到内存使用，
     *  因此实体类Role要实现序列化接口（否则会报未序列化异常）
     */
    @Test
    public void testSecondLevelCache(){
        SqlSession session1 = factory.openSession();
        IRoleDao roleDao1 = session1.getMapper(IRoleDao.class);
        List<Role> roles1 = roleDao1.listRoles();
        System.out.println(roles1);
        // 一级缓存消失
        session1.close();

        SqlSession session2 = factory.openSession();
        IRoleDao roleDao2 = session2.getMapper(IRoleDao.class);
        List<Role> roles2 = roleDao2.listRoles();
        System.out.println(roles2);

        System.out.println(roles1 == roles2);
    }
}
