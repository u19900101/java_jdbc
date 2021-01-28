package dao;

import pojo.User;

/**
 * @author lppppp
 * @create 2020-12-31 19:30
 */
public interface UserDao {
    User getUserByName(String name);
    User getUserByNameAndPass(String name,String password);
    int saveUser(User user);
}
