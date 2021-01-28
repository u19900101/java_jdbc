package pojo;

import java.math.BigDecimal;

/**
 * @author lppppp
 * @create 2021-01-08 14:40
 */
public class OrderItem {
   private Integer id;
   private  String name;
   private Integer count;
   private BigDecimal price;
   // private BigDecimal totalPrice;
   private String order_id;

    public OrderItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(count));
    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public OrderItem(String name, Integer count, BigDecimal price, String order_id) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.order_id = order_id;
    }
}
