import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2020-12-30 21:50
 */
public class _3_ServletContext extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletContext servletContext = getServletConfig().getServletContext();
        ServletContext servletContext = getServletContext();
        String username = servletContext.getInitParameter("username");
        System.out.println(username);

        String contextPath = servletContext.getContextPath();
        System.out.println("获取当前工程路径 ： " + contextPath); //  /5_web

        String realPath = servletContext.getRealPath("/");
        // D:\MyJava\4_javaweb\out\artifacts\5_web_war_exploded\
        System.out.println(realPath);

        //获取全局变量
        Object key = servletContext.getAttribute("key");
        System.out.println(key);
    }
}
