package primary;

import org.junit.jupiter.api.Test;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author lppppp
 * @create 2020-12-26 10:14
 *
 * 演示批量出入
 * 1.Statament
 * 2.preparedStatement
 *
 */
public class _7_batchInsert {
    // 耗时 12687
    @Test
    public void T1(){
        Connection conn = null;
        Statement statement = null;
        try {
            conn = JDBCUtil.getConn();
            statement = conn.createStatement();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                String sql = "insert into goods (name) values ('name_"+i+"')";
                statement.execute(sql);
            }
            System.out.println(System.currentTimeMillis()-start);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 改用 preparedStatement 进行批量插入
    // 耗时  13595
    @Test
    public void T2(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConn();
            long start = System.currentTimeMillis();
            // 1  "insert into goods(name)values(?)";
            // 2  "insert into goods(name)values(?)"
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <= 100; i++) {
                ps.setObject(1,"name_"+i);
                // 此处要和 statement区开  执行中没有参数 ！！！！！！！！！！！！
                ps.execute();
            }
            System.out.println(System.currentTimeMillis()-start);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 开启批处理功能 更换数据库驱动
    // 插入 100条 耗时 974  效率高10倍
    // 插入 20000条 耗时 9824

    @Test
    public void T3(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConn();
            long start = System.currentTimeMillis();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 20000; i++) {
                ps.setObject(1,"name_"+i);
                //1.“攒”sql
                ps.addBatch();
                if(i%500 == 0){
                    //2. 执行
                    ps.executeBatch();
                    //3. 清空
                    ps.clearBatch();
                }
            }
            System.out.println(System.currentTimeMillis()-start);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 设置 停止自动提交  2w 条 飞奔 775 再次提高十倍
    // 100w 条  24381

    @Test
    public void T4(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConn();
            conn.setAutoCommit(false);
            long start = System.currentTimeMillis();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 20000; i++) {
                ps.setObject(1,"name_"+i);
                //1.“攒”sql
                ps.addBatch();
                if(i%500 == 0){
                    //2. 执行
                    ps.executeBatch();
                    //3. 清空
                    ps.clearBatch();
                }
            }
            conn.commit();
            System.out.println(System.currentTimeMillis()-start);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
