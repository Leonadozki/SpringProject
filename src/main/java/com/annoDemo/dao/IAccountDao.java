package com.annoDemo.dao;

import com.annoDemo.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *  账户dao接口类( XML方式配置 )
 */
public interface IAccountDao {

    /**
     * @return 返回所有账户
     */
    List<Account> listAccounts();

    /**
     * @param
     * @return 返回所有account以及对应的user（一对一）
     */
    @Select("select * from account")
    @Results(id = "AccountUserMap", value = {
            @Result(property = "user", column = "uid",
                    one = @One(select = "com.annoDemo.dao.IUserDao.getById"))
    })
    List<Account> listAccountUser();

    /**
     * @return 通过用户id查询，返回所有账户
     */
    @Select("select * from account where uid=#{id}")
    List<Account> listAccountsByUid();
}
