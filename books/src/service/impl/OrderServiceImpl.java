package service.impl;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import pojo.Order;
import pojo.OrderItem;
import service.OrderService;

/**
 * @author lppppp
 * @create 2021-01-08 15:05
 */
public class OrderServiceImpl implements OrderService {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    @Override
    public void createOrder(Order order) {
        orderDao.createOrder(order);
    }

    @Override
    public void createOrderItem(OrderItem orderItem) {
        orderDao.createOrderItem(orderItem);
    }
}
