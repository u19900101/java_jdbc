package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import pojo.User;
import service.impl.UserServiceImpl;

import static org.junit.Assert.*;

/**
 * @author lppppp
 * @create 2020-12-31 20:06
 */

public class UserServiceImplTest {
    UserServiceImpl userService = new UserServiceImpl();
    User user = new User("admin12", "k1", "kk@qq.com");
    @Test
    public void register() {
        userService.register(user);
    }

    @Test
    public void login() {
        user.setPassword("k2");
        userService.login(user);
    }

    @Test
    public void existUsername() {
        boolean k1 = userService.existUsername("admin");
        System.out.println(k1);
    }
}
