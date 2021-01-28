package dao;

import pojo.Order;
import pojo.OrderItem;

/**
 * @author lppppp
 * @create 2021-01-08 15:14
 */
public interface OrderDao {
    void createOrder(Order order);

    void createOrderItem(OrderItem orderItem);
}
