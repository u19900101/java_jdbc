package web;

import pojo.Book;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author lppppp
 * @create 2021-01-04 13:37
 */
public class BookServlet extends BaseServlet {
    BookServiceImpl bookService = new BookServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,res);
    }

    protected void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("come into add ...");
        Book book = WebUtils.copyBean(req.getParameterMap(), new Book());
        int i = bookService.addBook(book);
        System.out.println("added book Num:" + i);
        //此处要用 重定向 不然 在提交页面刷新会再次调用 add 方法数据的二次提交
        // req.getRequestDispatcher("/manage/bookServlet?action=list").forward(req,res);
        // 默认地址为端口号

        // request.getContextPath()可以返回当前页面所在的应用的名字;
        res.sendRedirect(req.getContextPath()+"/manage/bookServlet?action=list");
    }

}
