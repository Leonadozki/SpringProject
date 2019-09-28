package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

public class JdbcConfig {
    /**
     * 创建QueryRunner对象
     * */
    @Bean("runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     */
    @Bean("dataSource")
    public DataSource createDataSource(){
        try {
            // 创建c3p0连接池
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/javatest?serverTimezone=CTT");
            ds.setUser("root");
            ds.setPassword("4lgwleo");
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
