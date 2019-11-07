package com.annoDemo.dao;

import com.annoDemo.domain.QueryVo;
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
     *  查询所有用户, 并且查出所有对应账户
     * */
//    @Select("select * from user")
    List<User> listAll();

    /**
     * @return 查询所有用户，并查出所有对应权限
     */
    List<User> listUserRole();

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

    /**
     * @return 所有user记录个数
     */
    @Select("select count(*) from user")
    Integer getTotalUserNumbers();


    /**
     * @param vo
     * @return 根据QueryVo条件查询User
     */
    @Select("select * from user where username like CONCAT('%',#{user.username},'%')")
    List<User> listUserByVo(QueryVo vo);
}
