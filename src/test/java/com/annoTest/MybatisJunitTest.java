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


/**
 *  dao层单元测试类
 *  注意： 单元测试应使用assert自动测试验证，避免人肉验证测试，以实现定时运行和代码修改提交后自动运行
 */
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


    /**
     *  修改user方法
     */
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(41);
        user.setUsername("老王改");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("南岸区弹子石2");
        userDao.updateUser(user);
        List<User> users = userDao.listAll();
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    /**
     * 删除user方法
     */
    @Test
    public void testDeleteUser(){
        Integer id = 48;
        userDao.removeUser(id);
        List<User> users = userDao.listAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     *  通过ID查找单个user
     */
    @Test
    public void testGetUser(){
        Integer id= 46;
        User user = userDao.getById(id);
        if (user!=null){
            System.out.println("testGetUser() 方法执行成功");
        }else{
            System.out.println("testGetUser() 方法执行失败，未找到数据");
        }
    }

    /**
     *  通过名称模糊搜索
     */
    @Test
    public void testGetByName(){
        String name = "王";
        List<User> users = userDao.listByName(name);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     *  返回所有user记录个数
     */
    @Test
    public void testGetTotalUserNumbers(){
        Integer userNumber = userDao.getTotalUserNumbers();
        System.out.println("记录个数： " + userNumber);
    }

}
