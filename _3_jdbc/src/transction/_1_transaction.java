package transction;

import org.junit.jupiter.api.Test;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static primary._3_prepareStatement.update;

/**
 * @author lppppp
 * @create 2020-12-26 20:29
 *
 * 1.模拟事务处理
 * 2.加上事务之后的转账操作
 * 3.演示隔离级别
 */
public class _1_transaction {

    // 模拟事务处理
    @Test
    public void T1(){
        String sql1 = "update user_table set balance = balance+100 WHERE user = ?";
        int u1 = update(sql1, "AA");
        // 模拟异常
        System.out.println(10/0);
        String sql2 = "update user_table set balance = balance-100 WHERE user = ?";
        int u2 = update(sql2, "BB");

        if(u1>0 && u2>0){
            System.out.println("转账成功！");
        }
    }

    // 加上事务之后的转账操作
    public static int updateTrans(Connection connection,String sql, Object... args){

        PreparedStatement ps = null;
        try {
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
            JDBCUtil.closeResource(ps,null);
        }
        return 0;
    }

    @Test
    public void T2(){
        /*
        * 1.同一事务共用一个 connection
        * 2.关闭自动提交
        * 3.所有的DML操作完后再进行提交
        * */
        Connection conn = null;
        try {
            conn = JDBCUtil.getConn();
            //1. 开启事务
            conn.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance+100 WHERE user = ?";
            int u1 = updateTrans(conn,sql1, "AA");
            // 模拟异常
            System.out.println(10/0);
            String sql2 = "update user_table set balance = balance-100 WHERE user = ?";
            int  u2 = updateTrans(conn,sql2, "BB");
            // 2. 提交事务
            conn.commit();
            if(u1>0 && u2>0){
                System.out.println("转账成功！");
            }
        } catch (Exception e) {
            // 3. 若有异常，进行回滚!!!!!!!!!
            try {
                System.out.println("回滚");
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {

            //6.恢复每次DML操作的自动提交功能
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 演示隔离级别

}














