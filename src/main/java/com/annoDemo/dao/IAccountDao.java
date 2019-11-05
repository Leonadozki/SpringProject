package com.annoDemo.dao;

import com.annoDemo.domain.Account;

import java.util.List;

/**
 *  账户dao接口类
 */
public interface IAccountDao {

    /**
     * @return 返回所有账户
     */
    List<Account> listAccounts();
}
