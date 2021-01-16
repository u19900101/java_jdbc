package dao.impl;

import dao.BaseDao;
import dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pojo.Order;
import pojo.OrderItem;
import utils.DBUtils;

import java.sql.Connection;

/**
 * @author lppppp
 * @create 2021-01-08 15:15
 */
@Repository
@Transactional
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;
    @Override
    public void createOrder(Order order) {
        String sql ="INSERT INTO t_order(id,create_time,`count`,`totalPrice`,`user_id`) VALUES(?,?,?,?,?)";
        int update = jdbcTemplate.update(sql,order.getId(),order.getCreate_time(),
                order.getCount(),order.getTotalPrice(),order.getUser_id());
        if(update>0){
            System.out.println("添加了订单");
        }
    }

    @Override
    public void createOrderItem(OrderItem orderItem) {

        String sql ="INSERT INTO t_orderItem"
        +"(`name`,`count`,`price`,`totalPrice`,`order_id`)"
        +"VALUES(?,?,?,?,?)";
        int update = jdbcTemplate.update(sql,orderItem.getName(),orderItem.getCount()
        ,orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrder_id());
        if(update>0){
            System.out.println("添加了订单Item");
        }
    }
}
