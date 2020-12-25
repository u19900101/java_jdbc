import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author lppppp
 * @create 2020-12-25 14:24
 */
public class _3_prepareStatement {
    /* 演示 prepareStatement 进行CRUD */
    @Test
    public void T1() throws Exception {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("jdbc.properties"));
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String driveName = properties.getProperty("driverClass");
        String url = properties.getProperty("url");

        Class.forName(driveName);
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection);
    }
}
