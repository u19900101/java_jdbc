package primary;

import org.junit.jupiter.api.Test;
import util.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author lppppp
 * @create 2020-12-25 14:24
 */
public class _3_prepareStatement {
    /* 演示 prepareStatement 进行CRUD */
    @Test
    public void T1() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream("jdbc.properties"));
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String driveName = properties.getProperty("driverClass");
            String url = properties.getProperty("url");

            Class.forName(driveName);
            connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT  customers" +
                    "(NAME,email,birth)" +
                    "VALUES(?,?,?);";
            // 填充占位符
            ps = connection.prepareStatement(sql);
            ps.setString(1, "哪吒1");
            ps.setString(2, "kk@163.com");

           /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date parse = sdf.parse("1500-01-01");
            ps.setDate(3,new java.sql.Date(parse.getTime()));*/
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

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
    }
    /* 编写小工具进行包装 */
    @Test
    public void T2(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConn();
            String sql = "UPDATE customers SET NAME = ? WHERE id = ?";
            // 填充占位符
            ps = connection.prepareStatement(sql);
            ps.setString(1,"金庸");
            ps.setInt(2,24);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,connection);
        }
    }

    /* 进一步使用 可变参数 进行包装*/
    @Test
    public void T3(){
//        String sql = "UPDATE customers SET NAME = ? WHERE id = ?";
//        Object []obs = {"王小二",20};
        // 演示关键字 要加着重符号
        String sql = "UPDATE `order`  SET order_name = ? WHERE order_id = ?";
        System.out.println(update(sql, "kkffkk", 4));
    }
    /* delete 操作*/
    @Test
    public void T4(){
        String sql = "delete from customers WHERE id = ?";
        update(sql,21);
    }

    public int update(String sql,Object ...args){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConn();
            // 填充占位符
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //ps.execute(); //返回值为 true 表示 为 查询语句  为 false则为 增删改语句
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,connection);
        }
        return 0;
    }
}
