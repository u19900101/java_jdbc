package dao;

import org.junit.jupiter.api.Test;
import primary.Customer;
import util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author lppppp
 * @create 2020-12-27 15:44
 *
 * 通用的增删改
 * 加上抽象，表示外部不能造该对象，只能用于继承
 */
public abstract class BaseDAO {
    // 通用的增删改操作
    public static int update(Connection connection, String sql, Object... args){

        PreparedStatement ps = null;
        try {
            // 填充占位符
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,null);
        }
        return 0;
    }

    // 查询操作
    public static <T>T getInsance(Connection conn,Class<T> clazz,String sql,Object ...args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();

            if(resultSet.next()){
                // 根据查询到的结果 动态的将结果封装到对象中
                ResultSetMetaData metaData = resultSet.getMetaData();
                T t = clazz.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    // 用列名 对对象中的同名字段赋值
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(i + 1);

                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t,value);
                }
                return t;
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,null,resultSet);
        }
        return null;
    }


    /* 升级为多条 */
    public <T> ArrayList<T> getInsanceList(Connection conn,Class<T> clazz, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()){
                // 根据查询到的结果 动态的将结果封装到对象中
                ResultSetMetaData metaData = resultSet.getMetaData();
//                primary.Customer customer = new primary.Customer();
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
            JDBCUtil.closeResource(ps,null,resultSet);
        }
        return null;
    }

    // 返回 查询项目的条目数
    //比如 select count(*) from table
    //     select max(birth) from table
    public <E>E getValue(Connection conn,String sql,Object ... args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,null,rs);
        }
        return null;
    }
}
