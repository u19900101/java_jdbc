package web;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author lppppp
 * @create 2021-01-03 20:11
 */
public class UserServlet extends BaseServlet {
    UserServiceImpl userService = new UserServiceImpl();

    /*protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        String action = req.getParameter("action");
        // 利用反射改写
        Method method = null;
        try {
            method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 原始写法
        if("login".equalsIgnoreCase(action)){
            login(req,res);
        }else if("register".equalsIgnoreCase(action)){
            register(req,res);
        }
    }*/

    private void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("come into do register ...");
        // 封装User

      /*  String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
       */
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = WebUtils.copyBean(parameterMap,new User());


        String repwd = req.getParameter("repwd");
        String code = req.getParameter("code");
        req.setAttribute("username",user.getUsername());
        req.setAttribute("password",user.getPassword());
        req.setAttribute("repwd",repwd);
        req.setAttribute("email",user.getEmail());
        req.setAttribute("code",code);
        boolean existUsername = userService.existUsername(user.getUsername());
        if(existUsername){
            // 用户名已存在
            req.setAttribute("msg","用户名已存在");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req,res);
        }else {
            //注册成功
            userService.register(user);
            req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,res);
        }
    }



    private void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("come into login");

        User user = WebUtils.copyBean(req.getParameterMap(), new User());

       /* String username = req.getParameter("username");
        String password = req.getParameter("password");*/
        boolean login = userService.login(user);
        if(login){
            // login succeed
            req.setAttribute("msg","login succeed");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,res);
        }else {
            // failed
            req.setAttribute("msg","用户名或密码不存在");
            req.setAttribute("username", URLEncoder.encode(user.getUsername(), "UTF-8"));

            req.setAttribute("password",user.getPassword());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,res);
        }
    }


}
