package dao;

import org.junit.jupiter.api.Test;
import primary.Customer;
import util.JDBCUtil;

import javax.security.auth.kerberos.KerberosKey;
import java.sql.Connection;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lppppp
 * @create 2020-12-27 16:56
 */
class CustomersDaoImplTest {
    CustomersDaoImpl custimpl = new CustomersDaoImpl();
    Connection conn = JDBCUtil.getConn();
    @Test
    void insert(){
        custimpl.insert(JDBCUtil.getConn(),new Customer(1,"name1","123@QQ.com",
                new Date(System.currentTimeMillis())));
    }

    @Test
    void deleteById() {
        custimpl.deleteById(conn,25);
    }

    @Test
    void update() {
        custimpl.update(conn,new Customer(29,"马致远","25.com",
                new Date(System.currentTimeMillis())));
    }

    @Test
    void getById() {
        System.out.println(custimpl.getById(conn,1));
    }

    @Test
    void getAll() {
        custimpl.getAll(conn).forEach(System.out::println);
    }

    @Test
    void getCount() {
        Long count = custimpl.getCount(conn);
        System.out.println(count);
    }

    @Test
    void getMaxBirth() {
        System.out.println(custimpl.getMaxBirth(conn));
    }
}