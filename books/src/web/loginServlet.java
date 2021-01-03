package web;

import pojo.User;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author lppppp
 * @create 2021-01-01 8:53
 */
public class loginServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean login = userService.login(new User(username, password));
        if(login){
            // login succeed
            req.setAttribute("msg","login succeed");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,res);
        }else {
            // failed
            req.setAttribute("msg","用户名或密码不存在");
            req.setAttribute("username", URLEncoder.encode(username, "UTF-8"));

            req.setAttribute("password",password);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,res);
        }
    }
}
