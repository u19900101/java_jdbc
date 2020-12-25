import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author lppppp
 * @create 2020-12-25 9:32
 *
 * 建立 jdbc 与 数据库的连接
 */
public class _1_connectDemo {
    @Test
    public void T1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/myemployees";

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "kk");

        Connection connect = driver.connect(url, info);

        System.out.println(connect);

    }

    /* 2.使用 反射进行获取连接  增加可移植性*/
    @Test
    public void testConnection2() {
        try {
            //1.实例化Driver
            String className = "com.mysql.jdbc.Driver";
            Class clazz = Class.forName(className);
            Driver driver = (Driver) clazz.newInstance();

            //2.提供url，指明具体操作的数据
            String url = "jdbc:mysql://localhost:3306/books";

            //3.提供Properties的对象，指明用户名和密码
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "kk");

            //4.调用driver的connect()，获取连接
            Connection conn = driver.connect(url, info);
            System.out.println(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 3.使用 DriverManager */
    @Test
    public void T3() throws Exception {
        // 注册驱动  mysql 自动加载了 但还是不要省驱动的加载
        Class.forName("com.mysql.jdbc.Driver");
        /* 在类加载时 就已经通过 静态代码块 加载了 下面的代码 */
        // Class clazz = Class.forName("com.mysql.jdbc.Driver");
        // Driver driver = (Driver) clazz.newInstance();
        // DriverManager.registerDriver(driver);
        String url = "jdbc:mysql://localhost:3306/books";
        Connection connection = DriverManager.getConnection(url, "root", "kk");
        System.out.println(connection);
    }

    /* 4. 将配置信息写进文件 进行加载*/
    @Test
    public void T4() throws Exception {
        // 通过类的加载器获取文件的输入流
        InputStream in = _1_connectDemo.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driverName = properties.getProperty("driverClass");
        String url = properties.getProperty("url");

        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
