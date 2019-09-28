package com.annoTest;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * 测试QueryRunner是否单例,若是，要使其为多例
 * */
public class QueryRunnerTest {

    @Test
    public void runnerTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext("config");
        QueryRunner runner = (QueryRunner) ac.getBean("runner");
        QueryRunner runner1 = (QueryRunner) ac.getBean("runner");
        System.out.println(runner == runner1);

    }
}
