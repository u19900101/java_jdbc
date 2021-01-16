package web;

import config.TxConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.Book;
import pojo.Page;
import service.UserService;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author lppppp
 * @create 2021-01-04 13:37
 */
public class BookServlet extends BaseServlet {
    BookServiceImpl bookService = getBookService();

    protected void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manage/book_manage.jsp").forward(req,res);
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
        Page<Book> page = bookService.getPageList(1);
        res.sendRedirect(req.getContextPath()+"/manage/bookServlet?action=page&pageNo="+page.getPageTotal());
    }

    protected void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("come into delete ...");
        String id = req.getParameter("id");
        int i = bookService.deleteBookById(Integer.parseInt(id));
        System.out.println("delete " + i);
        // request.getContextPath()可以返回当前页面所在的应用的名字;
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        res.sendRedirect(req.getContextPath()+"/manage/bookServlet?action=page&pageNo="+pageNo);
    }


    protected void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("come into update ...");
        Book book = WebUtils.copyBean(req.getParameterMap(), new Book());
        int i = bookService.updateBookById(book);
        System.out.println("update " + i);
        // request.getContextPath()可以返回当前页面所在的应用的名字;
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        res.sendRedirect(req.getContextPath()+"/manage/bookServlet?action=page&pageNo="+pageNo);
    }

    // 为了传参使用
    protected void getBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("come into getBook ...");
        String id = req.getParameter("id");

        Book book = bookService.queryBookById(Integer.parseInt(id));
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        System.out.println(book);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manage/book_edit.jsp?pageNo="+pageNo).forward(req, res);
    }


    // 分页功能
    protected void page(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        Page<Book> page = bookService.getPageList(pageNo);
        page.setUrl("manage/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manage/book_manage.jsp").forward(req,res);
    }

    public static BookServiceImpl getBookService(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        BookServiceImpl bookService = context.getBean("bookServiceImpl", BookServiceImpl.class);
        return bookService;
    }

    @Test
    public void T(){
        System.out.println(bookService.getPageListByPrice(1,50, 5));
    }
}
