package util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author lppppp
 * @create 2020-12-25 15:19
 */
public class JDBCUtil {

    static public Connection getConn(){
        Connection connection = null;
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream("jdbc.properties"));
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String driveName = properties.getProperty("driverClass");
            String url = properties.getProperty("url");
            Class.forName(driveName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  connection;
    }

    static public void closeResource(PreparedStatement ps,Connection connection){
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void closeResource(PreparedStatement ps, Connection connection, ResultSet rs){
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
