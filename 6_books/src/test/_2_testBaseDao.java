package test;

import com.alibaba.druid.util.JdbcUtils;
import dao.BaseDao;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import pojo.User;
import utils.DBUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lppppp
 * @create 2020-12-31 17:42
 */
public class _2_testBaseDao {
    BaseDao baseDao = new BaseDao();
    Connection conn = DBUtils.getConn();

    @Test
    public void Tinsert(){

        String sql = "insert into t_user(username,password,email)values(?,?,?)";
        for (int i = 0; i < 10; i++) {
            int update = baseDao.update(conn, sql, "u_"+i,"p_"+i,"email_"+i);
        }
    }

    @Test
    public void Tdelete(){
        String sql = "delete from t_user where id = ?";
        int update = baseDao.update(conn, sql, 2);
        System.out.println(update);
    }

    @Test
    public void Tupdate(){
        String sql = "update  t_user set username = ? where id = ?";
        int update = baseDao.update(conn, sql, "u_4444",4);
        System.out.println(update);
    }

    @Test
    public void TgetInsance(){
        String sql = "select * from  t_user where id = ?";
        BeanHandler<User> userBeanHandler = new BeanHandler<>(User.class);
        User insance = baseDao.getInsance(conn, sql, userBeanHandler,1);
        System.out.println(insance);
    }

    @Test
    public void TgetInsanceList(){
        String sql = "select * from  t_user where id < ?";
        BeanListHandler<User> userBeanListHandler = new BeanListHandler<>(User.class);
        List<User> insanceList = baseDao.getInsanceList(conn, sql, userBeanListHandler, 10);
        insanceList.forEach(System.out::println);
    }

    @Test
    public void TsingleValue(){
        String sql = "select count(*) from  t_user";
        Object singleValue = baseDao.getSingleValue(conn, sql);
        System.out.println(singleValue);
    }
}
