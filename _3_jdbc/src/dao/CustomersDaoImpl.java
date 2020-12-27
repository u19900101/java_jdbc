package dao;

import primary.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lppppp
 * @create 2020-12-27 16:27
 */
public class CustomersDaoImpl extends BaseDAO implements CustomersDAO {
    @Override
    public void insert(Connection conn, Customer customer){
        String sql = "insert into customers (name,email,birth) values (?,?,?)";
        update(conn,sql,customer.getName(),customer.getEmail(),customer.getBirth());
    }

    @Override
    public void deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        update(conn,sql,id);
    }

    @Override
    public void update(Connection conn, Customer customer) {
        String sql = "update customers set name = ?,email = ?,birth = ? where id = ?";
        update(conn,sql,customer.getName(),customer.getEmail(),customer.getBirth(),customer.getId());
    }

    @Override
    public Customer getById(Connection conn, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        Customer customer = getInsance(conn, Customer.class, sql, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        ArrayList<Customer> customerArrayList = getInsanceList(conn, Customer.class, sql);
        return customerArrayList;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        return getValue(conn, sql);
    }
}
