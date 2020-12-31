package service;

import pojo.User;

/**
 * @author lppppp
 * @create 2020-12-31 19:56
 */
public interface UserService {
    public void register(User user);
    public void login(User user);
    public boolean existUsername(String username);
}
