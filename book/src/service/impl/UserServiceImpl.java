package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.User;
import service.UserService;

/**
 * @author lppppp
 * @create 2020-12-31 19:57
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public boolean register(User user) {
        int i = userDao.saveUser(user);
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
        String name=user.getUsername();
        String password = user.getPassword();
        User userByNameAndPass = userDao.getUserByNameAndPass(name, password);
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
        User userByName = userDao.getUserByName(username);
        if(userByName == null){
            System.out.println("username is available...");
            return false;
        }
        System.out.println("username is occupy...");
        return true;
    }
}
