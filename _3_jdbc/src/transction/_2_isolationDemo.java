package transction;

import org.junit.jupiter.api.Test;
import primary.User;
import primary._3_prepareStatement;
import util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;

import static primary._5_queryAdvance.getCustomerQuery;
import static transction._1_transaction.updateTrans;

/**
 * @author lppppp
 * @create 2020-12-26 22:17
 *
 * 隔离级别
 * TRANSACTION_READ_UNCOMMITTED = 1;
 * TRANSACTION_READ_COMMITTED   = 2;
 * TRANSACTION_REPEATABLE_READ  = 4;
 * TRANSACTION_SERIALIZABLE     = 8;
 *
 * 设置
 * SET GLOBAL TRANSACTION ISOLATION LEVEL READ COMMITTED;
 *
 */
public class _2_isolationDemo {
    // 获取隔离级别
    @Test
    public void T1() throws SQLException {
        Connection conn = JDBCUtil.getConn();

        int transactionIsolation = conn.getTransactionIsolation();
        System.out.println(transactionIsolation);// 4 为 可重复读
    }

    //  演示 read uncommitted 对数据库进行操作，但是不提交，15s后关闭
    @Test
    public void Tc1() throws SQLException, InterruptedException {
        String sql = "update user_table set balance = 4000 where user = ?";
        Connection conn = JDBCUtil.getConn();
        conn.setAutoCommit(false);
        updateTrans(conn,sql,"CC");

        sql = "select * from user_table where user = ?";
        User cc = getCustomerQuery(User.class, sql, "CC");
        System.out.println(cc);
        Thread.sleep(15000);
        JDBCUtil.closeResource(null,conn);
    }


    @Test
    public void Tc2() throws SQLException {
        String sql = "select * from user_table where user = ?";
        Connection conn = JDBCUtil.getConn();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        User cc = getInstance(conn,User.class, sql, "CC");
        System.out.println(cc);
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T>T getInstance(Connection conn,Class<T> clazz,String sql,Object ...args){
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

}
