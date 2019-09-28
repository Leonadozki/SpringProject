package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @Configuration 声明当前类为配置类
 * @ComponentScan 告知SpringIoc容器创建时扫描哪个范围的包
 * */

@Configuration
@ComponentScan("com.annoDemo")
// 导入子配置类，逻辑较清晰一点
@Import(JdbcConfig.class)
public class SpringConfiguration {

    // 构造方法
    public SpringConfiguration() {
        System.out.println("容器初始化，启动成功...");
    }

}
