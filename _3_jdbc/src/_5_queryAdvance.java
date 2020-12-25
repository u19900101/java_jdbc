import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.net.SocketTimeoutException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author lppppp
 * @create 2020-12-25 19:48
 *
 * 动态的运用 泛型来提高通用性
 */
public class _5_queryAdvance {

    @Test
    public void T(){
        String sql = "select email,id,name from customers where id = ?";
        Customer customerQuery = getCustomerQuery(Customer.class, sql, 12);
        System.out.println(customerQuery);

        String sql2 = "select name username,password from user where id = ?";
        User user = getCustomerQuery(User.class, sql2, 2);
        System.out.println(user);
    }
    public <T>T getCustomerQuery(Class<T> clazz,String sql,Object ...args){
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
//                Customer customer = new Customer();
                T t = clazz.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    // 用列名 对对象中的同名字段赋值
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(i + 1);

                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t,value);
                }
//                System.out.println(customer);
                return t;
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,conn,resultSet);
        }
        return null;
    }


    /* 升级为多条 */
    @Test
    public void T2(){
        String sql = "select email,id,name from customers where id < ?";
        ArrayList<Customer> customerQuery = getQueryList(Customer.class, sql, 5);
        customerQuery.forEach(System.out::println);
    }
    public <T> ArrayList<T> getQueryList(Class<T> clazz, String sql, Object ...args){
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
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()){
                // 根据查询到的结果 动态的将结果封装到对象中
                ResultSetMetaData metaData = resultSet.getMetaData();
//                Customer customer = new Customer();
                T t = clazz.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    // 用列名 对对象中的同名字段赋值
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(i + 1);

                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t,value);
                }
                list.add(t);
            }
            return list;
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,conn,resultSet);
        }
        return null;
    }
}
