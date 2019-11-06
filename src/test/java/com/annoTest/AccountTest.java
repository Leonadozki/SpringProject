package com.annoTest;

import com.annoDemo.dao.IAccountDao;
import com.annoDemo.domain.Account;
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

public class AccountTest {

    private SqlSession session;
    private InputStream in;
    private IAccountDao accountDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDao.class);

    }

    @After
    public void destroy() throws IOException {
        session.close();
        in.close();
    }

    @Test
    public void testListAccounts() {
        List<Account> accounts = accountDao.listAccounts();
        for (Account account:accounts){
            System.out.println("------------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
}
