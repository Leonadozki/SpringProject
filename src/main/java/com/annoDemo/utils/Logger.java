package com.annoDemo.utils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect        // 声明为切面类
@Component("logger")
public class Logger {

    @Resource(name = "transactionManager")
    private TransactionManager tsManager;

    @Pointcut("execution(* com.annoDemo.service.ResolveServiceImpl.*(..))")
    private void pc(){}


    /**
     * 环绕通知
     * */
    @Around("pc()")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint){

        Object rtValue = null;

        try {
            // 获取Args传入proceed（）方法
            Object[] args = proceedingJoinPoint.getArgs();

            tsManager.beginTransaction();
            System.out.println("前置通知 - 开始事务 beginTransaction()执行了！");

            // 调用业务层方法，proceed()方法会返回一个Object类型对象
            rtValue = proceedingJoinPoint.proceed(args);

            tsManager.commit();
            System.out.println("后置通知 - 提交事务 commit()执行了！");

            return rtValue;

        } catch (Throwable throwable) {

            tsManager.rollback();
            System.out.println("异常通知 - 事务回滚 rollback()执行了！");

            throw new RuntimeException(throwable);

        }finally {

            tsManager.release();
            System.out.println("最终通知 - 释放连接 release()执行了！");
        }
    }
}
