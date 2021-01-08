package pojo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lppppp
 * @create 2021-01-08 14:36
 */

public class Order {
    private String id;
    private String create_time;
    private Integer count;
    private BigDecimal totalPrice;
    private Integer user_id;
    public Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", user_id='" + user_id + '\'' +
                ", status=" + status +
                '}';
    }

    public Order(String id, String create_time, Integer count, BigDecimal totalPrice, Integer user_id, Status status) {
        this.id = id;
        this.create_time = create_time;
        this.count = count;
        this.totalPrice = totalPrice;
        this.user_id = user_id;
        this.status = status;
    }

    public Order() {
    }
}
