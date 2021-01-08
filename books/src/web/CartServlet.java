package web;

import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author lppppp
 * @create 2021-01-07 21:45
 */
public class CartServlet extends BaseServlet {

    protected void updateCount(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int count = Integer.parseInt(req.getParameter("count"));
        System.out.println("come into updateCount");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart.getItems().get(id).getCount()<count){
            req.getSession().setAttribute("lastAddBook",cart.getItems().get(id).getName());
        }
        cart.updateCount(id,count);
        res.sendRedirect(req.getHeader("Referer"));

    }
    protected void clearCart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        res.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("come into deleteItem");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        res.sendRedirect(req.getHeader("Referer"));
    }
    protected void addItem(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("come into addItem");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 判断是否有购物车
        // 无购物车则创建
        if(cart== null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        BookServiceImpl bookService = new BookServiceImpl();
        Book book = bookService.queryBookById(id);

        CartItem cartItem =new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice().multiply(new BigDecimal(1)));
        cart.addItem(cartItem);

        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem> itemEntry : items.entrySet()) {

            System.out.println(itemEntry.getKey() + "  "+ itemEntry.getValue());
        }
        // 重定向回原来商品所在的地址页面
        res.sendRedirect(req.getHeader("Referer"));
        // res.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");

        req.getSession().setAttribute("lastAddBook",book.getName());

    }
}
