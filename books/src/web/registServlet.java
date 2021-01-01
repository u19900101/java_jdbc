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
 * @create 2020-12-31 20:48
 */
public class registServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest req , HttpServletResponse res ) throws ServletException, IOException {

        System.out.println("come into do post ...");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repwd = req.getParameter("repwd");
        String email = req.getParameter("email");

        boolean existUsername = userService.existUsername(username);
        if(existUsername){
            req.getRequestDispatcher("/pages/user/regist.html").forward(req,res);
        }else {
            userService.register(new User(username,password,email));
            req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,res);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }
}
