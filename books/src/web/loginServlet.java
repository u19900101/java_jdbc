package web;

import pojo.User;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            req.getRequestDispatcher("/pages/user/login_success.html").forward(req,res);
        }else {
            // failed
            req.getRequestDispatcher("/pages/user/login.html").forward(req,res);
        }
    }
}
