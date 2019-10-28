package config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Configuration 声明当前类为配置类
 * @ComponentScan 告知SpringIoc容器创建时扫描哪个范围的包
 * */

@Configuration
@ComponentScan("com.annoDemo")
@Import({JdbcConfig.class, TransactionConfig.class})       // 导入子配置类，逻辑较清晰一点
@PropertySource("classpath:JdbcConfig.properties")
@EnableAspectJAutoProxy        // 开启aop注解
//@EnableTransactionManagement   // 开启事务支持注解
public class SpringConfiguration {

    // 构造方法
    public SpringConfiguration() {
        System.out.println("容器初始化，启动成功...");
    }

}
