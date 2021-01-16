package service.impl;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Order;
import pojo.OrderItem;
import service.OrderService;

/**
 * @author lppppp
 * @create 2021-01-08 15:05
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Override
    public void createOrder(Order order) {
        orderDao.createOrder(order);
    }

    @Override
    public void createOrderItem(OrderItem orderItem) {
        orderDao.createOrderItem(orderItem);
    }
}
