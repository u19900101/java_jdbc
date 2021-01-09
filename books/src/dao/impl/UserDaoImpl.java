package dao.impl;

import dao.BaseDao;
import dao.UserDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.User;
import utils.DBUtils;

import java.sql.Connection;

/**
 * @author lppppp
 * @create 2020-12-31 19:28
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User getUserByName(String name) {
        BaseDao baseDao = new BaseDao();
        Connection conn = DBUtils.getConn();
        String sql = "select * from t_user where username = ?";
        User insance = baseDao.getInsance(conn, sql, new BeanHandler<>(User.class), name);
        return insance;
    }

    @Override
    public User getUserByNameAndPass(String name, String password) {
        BaseDao baseDao = new BaseDao();
        Connection conn = DBUtils.getConn();
        String sql = "select * from t_user where username = ? and password = ?";
        User insance = baseDao.getInsance(conn, sql, new BeanHandler<>(User.class), name,password);
        return insance;
    }

    @Override
    public int saveUser(User user) {
        BaseDao baseDao = new BaseDao();
        Connection conn = DBUtils.getConn();
        String sql = "insert into t_user(username,password,email)values(?,?,?)";
        int update = baseDao.update(conn, sql, user.getUsername()
                ,user.getPassword(),user.getEmail());
        if(update>0){
            System.out.println(" 保存成功 ...");
        }
        return update;
    }
}
