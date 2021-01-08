package service.impl;

import dao.impl.UserDaoImpl;
import pojo.User;
import service.UserService;

/**
 * @author lppppp
 * @create 2020-12-31 19:57
 */
public class UserServiceImpl implements UserService {

    UserDaoImpl userDaoImpl = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        int i = userDaoImpl.saveUser(user);
        if(i > 0){
            System.out.println("register succeed...");
            return true;
        }else {
            System.out.println("register failed...");
            return false;
        }
    }

    @Override
    public User login(User user) {
        User userByNameAndPass = userDaoImpl.getUserByNameAndPass(user.getUsername(), user.getPassword());
        if(userByNameAndPass!= null){
            System.out.println("login succeed...");
            return userByNameAndPass;
        }else {
            System.out.println("login failed...");
        }
        return null;
    }

    @Override
    public boolean existUsername(String username) {
        User userByName = userDaoImpl.getUserByName(username);
        if(userByName == null){
            System.out.println("username is available...");
            return false;
        }
        System.out.println("username is occupy...");
        return true;
    }
}
