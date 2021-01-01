package service;

import pojo.User;

/**
 * @author lppppp
 * @create 2020-12-31 19:56
 */
public interface UserService {
    public boolean register(User user);
    public boolean login(User user);
    public boolean existUsername(String username);
}
