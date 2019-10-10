package com.annoDemo.utils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect        // 声明为切面类
@Component("logger")
public class Logger {

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

            System.out.println("前置通知 - beforeLog执行了！");

            // 调用业务层方法，proceed()方法会返回一个Object类型对象
            rtValue = proceedingJoinPoint.proceed(args);

            System.out.println("后置通知 - AfterReturningLog执行了！");

            return rtValue;

        } catch (Throwable throwable) {

            System.out.println("异常通知 - AfterThrowingLog执行了！");

            throw new RuntimeException(throwable);

        }finally {

            System.out.println("最终通知 - AfterLog执行了！");
        }
    }
}
