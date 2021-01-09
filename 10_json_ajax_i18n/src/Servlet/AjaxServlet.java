package Servlet;

import com.google.gson.Gson;
import pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @author lppppp
 * @create 2021-01-09 20:23
 */
public class AjaxServlet extends BaseServlet {
    protected void javaScriptAjax(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Ajax coming");
        res.getWriter().write(new Gson().toJson(new Person(new Random().nextInt(1000), "小王子")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void jQueryAjax(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("jQueryAjax coming");
        res.getWriter().write(new Gson().toJson(new Person(new Random().nextInt(1000), "小王子")));
    }
    protected void jQueryGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("jQueryGet coming");
        res.getWriter().write(new Gson().toJson(new Person(new Random().nextInt(1000), "小王子")));
    }
    protected void jQueryPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("jQueryPost coming");
        res.getWriter().write(new Gson().toJson(new Person(new Random().nextInt(1000), "小王子")));
    }


    protected void jQueryGetJSON(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("jQueryGetJSON coming");
        res.getWriter().write(new Gson().toJson(new Person(new Random().nextInt(1000), "小王子")));
    }

    /* 前后端数据完全交互 */
    protected void jQuerySerialize(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("jQuerySerialize coming");
        Object username = req.getParameter("username");
        Object password = req.getParameter("password");

        System.out.println(username+"___"+password);
        res.getWriter().write(new Gson().toJson(new Person(new Random().nextInt(1000), "小王子")));
    }




}
