package dao;

import pojo.User;

/**
 * @author lppppp
 * @create 2020-12-31 19:30
 */
public interface UserDao {
    public User getUserByName(String name);
    public User getUserByNameAndPass(String name,String password);
    public int saveUser(User user);
}
