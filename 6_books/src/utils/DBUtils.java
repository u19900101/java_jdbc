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
    private static DataSource dataSource = null;
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
        }

    }

    public static Connection getConn(){
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeResource(Connection conn){
        JdbcUtils.close(conn);
    }

}
