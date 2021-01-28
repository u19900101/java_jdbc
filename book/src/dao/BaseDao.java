package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lppppp
 * @create 2020-12-31 17:19
 *
 * 利用jdbcUtils 写通用的操作数据库方法
 */
public class BaseDao {
    // update insert delete操作
    public int update(Connection conn,String sql,Object ...args){
        QueryRunner queryRunner = new QueryRunner();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // select 操作
    // 单条查询

    public <T> T getInsance(Connection conn, String sql, BeanHandler<T> beanHandler,Object...args){
        QueryRunner queryRunner = new QueryRunner();
        try {
            return queryRunner.query(conn,sql,beanHandler,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 多条查询
    public <T> List<T> getInsanceList(Connection conn, String sql, BeanListHandler<T> beanListHandler,Object...args){
        QueryRunner queryRunner = new QueryRunner();
        ArrayList<T> list = new ArrayList<T>();
        try {
            return queryRunner.query(conn,sql,beanListHandler,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 特殊值查询
    public Object getSingleValue(Connection conn,String sql,Object ...args){
        QueryRunner queryRunner = new QueryRunner();

        try {
            Object value = queryRunner.query(conn, sql, new ScalarHandler(), args);
            return  value;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
