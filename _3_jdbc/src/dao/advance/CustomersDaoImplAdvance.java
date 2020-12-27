package dao.advance;

import dao.CustomersDaoImpl;
import primary.Customer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lppppp
 * @create 2020-12-27 17:32
 * 升级优化 省去类的获取
 *
 * 要写全  BaseDAOAdvance<Customer>  才能利用反射 得到类
 */
public class CustomersDaoImplAdvance extends
        BaseDAOAdvance<Customer> implements CustomersDAOAdvance{

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

    public void update(Connection conn, Customer customer) {
        String sql = "update customers set name = ?,email = ?,birth = ? where id = ?";
        update(conn,sql,customer.getName(),customer.getEmail(),customer.getBirth(),customer.getId());
    }


    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return (Long) getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        return (Date) getValue(conn, sql);
    }

    @Override
    public Customer getById(Connection conn, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        Customer customer = (Customer) getInsance(conn,sql, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        ArrayList<Customer> customerArrayList = getInsanceList(conn,sql);
        return customerArrayList;
    }

}
