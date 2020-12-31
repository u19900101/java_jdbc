package test;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import pojo.User;

/**
 * @author lppppp
 * @create 2020-12-31 19:41
 */

public class UserDaoImplTest {
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    @Test
    public void getUserByName() {
        User admin = userDaoImpl.getUserByName("admin");
        System.out.println(admin);
    }

    @Test
    public void getUserByNameAndPass() {
        User userByNameAndPass = userDaoImpl.getUserByNameAndPass("admin", "admin");
        System.out.println(userByNameAndPass);
    }

    @Test
    public void saveUser() {
        int i = userDaoImpl.saveUser(new User("admin1", "k1", "kk@qq.com"));
        System.out.println(i);
    }
}
