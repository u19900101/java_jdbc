package web;
import com.google.gson.Gson;
import config.TxConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pojo.Book;
import pojo.Page;
import pojo.User;
import service.UserService;
import service.impl.BookServiceImpl;
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
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author lppppp
 * @create 2021-01-03 20:11
 */

public class UserServlet extends BaseServlet {

    public UserService userService=getUserService();
    private void ajaxexistUsername(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String username = req.getParameter("username");
        boolean existUsername = userService.existUsername(username);

        HashMap<String, Boolean> map = new HashMap<>();
        map.put("existUsername", existUsername);
        res.getWriter().write( new Gson().toJson(map));

    }

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

        /*防止用户重复提交*/
        //获取session
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 将session中的该值置为null
        // req.getSession().setAttribute(KAPTCHA_SESSION_KEY,null);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //比较验证码
        boolean existUsername = userService.existUsername(user.getUsername());
        if(token!=null && token.equalsIgnoreCase(code)){
            if(existUsername){
                // 用户名已存在
                req.setAttribute("msg","用户名已存在");
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req,res);
            }else {
                //注册成功
                userService.register(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,res);
            }
        }else {
            req.setAttribute("msg","验证码有误，请重新输入");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req,res);
        }

    }
    private void logout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        //还要清空购物车session
        req.getSession().removeAttribute("cart");
        res.sendRedirect("index.jsp");
    }
    private void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("come into login");

        User user = WebUtils.copyBean(req.getParameterMap(), new User());
        User login = userService.login(user);
        if(login!=null){
            // login succeed
            req.setAttribute("msg","login succeed");
            req.getSession().setAttribute("user",login);
            req.getSession().setMaxInactiveInterval(60*60*24*7);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,res);
        }else {
            // failed
            req.setAttribute("msg","用户名或密码不存在");
            req.setAttribute("username", URLEncoder.encode(user.getUsername(), "UTF-8"));

            req.setAttribute("password",user.getPassword());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,res);
        }
    }

    public static UserService getUserService(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        return userService;
    }

    @Test
    public void T2(){
        System.out.println(userService);
    }
    @Test
    public void T_allconfig(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        boolean register = userService.register(new User("kk11112", "kk1"));
        System.out.println(register);
    }
}

