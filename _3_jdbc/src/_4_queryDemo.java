import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author lppppp
 * @create 2020-12-25 16:20
 */
public class _4_queryDemo {
    /* 查询演示 */
    @Test
    public void T1(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "select * from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,7);
            resultSet = ps.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,conn,resultSet);
        }
    }

    // 加强版 通用查询
    @Test
    public void T2(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "select email,id,name,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,7);
            resultSet = ps.executeQuery();

            if(resultSet.next()){
                // 根据查询到的结果 动态的将结果封装到对象中
                ResultSetMetaData metaData = resultSet.getMetaData();
                Customer customer = new Customer();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    // 用列名 对对象中的同名字段赋值
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(i + 1);

                    Field declaredField = Customer.class.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(customer,value);
                }
                System.out.println(customer);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,conn,resultSet);
        }
    }

    @Test
    public void T3(){
        String sql = "select email,id,name from customers where id = ?";
        Customer customerQuery = getCustomerQuery(sql, 10);
        System.out.println(customerQuery);
    }
    // 封装
    public Customer getCustomerQuery(String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtil.getConn();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();

            if(resultSet.next()){
                // 根据查询到的结果 动态的将结果封装到对象中
                ResultSetMetaData metaData = resultSet.getMetaData();
                Customer customer = new Customer();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    // 用列名 对对象中的同名字段赋值
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(i + 1);

                    Field declaredField = Customer.class.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(customer,value);
                }
//                System.out.println(customer);
                return customer;
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,conn,resultSet);
        }
        return null;
    }
}
