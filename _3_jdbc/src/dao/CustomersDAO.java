package dao;

import primary.Customer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @author lppppp
 * @create 2020-12-27 16:18
 *
 * 针对特定的类定义增删改查接口
 */
public interface CustomersDAO {
    void insert(Connection conn, Customer customer);
    void deleteById(Connection conn, int id);
    void update(Connection conn, Customer customer);
    Customer getById(Connection conn,int id);

    List<Customer> getAll(Connection conn);

    Long getCount(Connection conn);

    Date getMaxBirth(Connection conn);
}
