package web;

import pojo.Book;
import pojo.Page;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2021-01-06 10:47
 */
public class ClientServlet extends BaseServlet {
    BookServiceImpl bookService = BookServlet.getService();
    protected void page(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("come into ClientServlet...");
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        Page<Book> page = bookService.getPageList(pageNo);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,res);
    }

    /*******/
    protected void queryByPrice(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("come into queryByPrice...");
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), 100000);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);

        Page<Book> page = bookService.getPageListByPrice(pageNo,max,min);
        page.setUrl("client/bookServlet?action=queryByPrice&min="+min+"&max="+max);
        req.setAttribute("page", page);
        // 用于回显 查询的 价格数据
        /*if(!"".equalsIgnoreCase(req.getParameter("max"))){
            req.setAttribute("max", max);
        }
        if(!"".equalsIgnoreCase(req.getParameter("min"))){
            req.setAttribute("min", min);
        }*/
        req.setAttribute("min", req.getParameter("min"));
        req.setAttribute("max", req.getParameter("max"));
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,res);
    }

}
