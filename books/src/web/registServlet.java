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
        String code = req.getParameter("code");

        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.setAttribute("repwd",repwd);
        req.setAttribute("email",email);
        req.setAttribute("email",code);


        boolean existUsername = userService.existUsername(username);
        if(existUsername){
            // 用户名已存在
            req.setAttribute("msg","用户名已存在");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,res);
        }else {
            //注册成功
            userService.register(new User(username,password,email));
            req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,res);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }
}
