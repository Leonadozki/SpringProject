package com.annoDemo.dao;

import com.annoDemo.domain.Account;

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
     * @param uid 根据用户ID查询账户
     * @return
     */
    List<Account> listAccountByUid(Integer uid);
}
