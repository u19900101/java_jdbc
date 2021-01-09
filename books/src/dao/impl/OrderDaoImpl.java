package dao.impl;

import dao.BaseDao;
import dao.OrderDao;
import pojo.Order;
import pojo.OrderItem;
import utils.DBUtils;

import java.sql.Connection;

/**
 * @author lppppp
 * @create 2021-01-08 15:15
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public void createOrder(Order order) {
        Connection conn = DBUtils.getConn();
        String sql ="INSERT INTO t_order(id,create_time,`count`,`totalPrice`,`user_id`) VALUES(?,?,?,?,?)";
        int update = update(conn, sql,order.getId(),order.getCreate_time(),
                order.getCount(),order.getTotalPrice(),order.getUser_id());
        if(update>0){
            System.out.println("添加了订单");
        }
    }

    @Override
    public void createOrderItem(OrderItem orderItem) {
        Connection conn = DBUtils.getConn();
        String sql ="INSERT INTO t_orderItem"
        +"(`name`,`count`,`price`,`totalPrice`,`order_id`)"
        +"VALUES(?,?,?,?,?)";
        int update = update(conn, sql,orderItem.getName(),orderItem.getCount()
        ,orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrder_id());
        if(update>0){
            System.out.println("添加了订单Item");
        }
    }
}
