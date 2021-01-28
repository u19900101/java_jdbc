package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author lppppp
 * @create 2020-12-31 16:35
 */
public class DBUtils {
    private static DataSource dataSource;
    public static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    static {
        try {
            Properties pros = new Properties();
            InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("druid.properties");
//            InputStream is = ClassLoader.getSystemResourceAsStream("druid.properties");
            pros.load(is);
            //根据提供的BasicDataSourceFactory创建对应的DataSource对象
            dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        // 连接为空时进行创建
        if(conn==null){
            try {
                conn = dataSource.getConnection();
                // 设置自动提交为false
                threadLocal.set(conn);
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return conn;
    }

    public static void commitAndcloseResource(){
        Connection connection =  threadLocal.get();
        if (connection != null) { // 如果不等于null，说明 之前使用过连接，操作过数据库
            try {
                connection.commit(); // 提交 事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        threadLocal.remove();
    }
    public static void rollbackAndcloseResource(){
        Connection connection = threadLocal.get();
        if (connection != null) { // 如果不等于null，说明 之前使用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        threadLocal.remove();
    }

}
