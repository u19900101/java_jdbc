package config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lppppp
 * @create 2021-01-14 22:33
 */

// 完全注解开发
@Configuration
@ComponentScan(basePackages = {"service","web","dao"})
@EnableTransactionManagement    //开启事务
public class TxConfig {
    //创建数据库连接池
    @Bean
    public DataSource getDruidDataSource() {
        DruidDataSource dataSource =  new DruidDataSource();
        Properties pros = new Properties(); //建立一个映射的properties类
        InputStream in=null;
        try {
            in = new BufferedInputStream(new FileInputStream("D:\\MyJava\\4_javaweb\\book\\src\\druid.properties"));//根据文件获取流
            pros.load(in);//加载流
            String driverClassName = pros.getProperty("driverClassName");
            String url = pros.getProperty("url");
            String username = pros.getProperty("username");
            String password = pros.getProperty("password");
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
        }catch (Exception e){
            System.out.println(e);
        }

        return dataSource;
    }
    //创建 JdbcTemplate 对象
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        //到 ioc 容器中根据类型找到 dataSource
        JdbcTemplate jdbcTemplate =  new JdbcTemplate();
        //注入 dataSource
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    //创建事务管理器
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager =  new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
