import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2020-12-30 19:53
 */
public class _2_ServletConfig extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //super.init();
        System.out.println("2 init  初始化方法");
        // 1 、可以获取 Servlet 程序的别名 servlet-name 的值
        System.out.println("HelloServlet  程序的别名是:" + servletConfig.getServletName());
        // 2 、获取初始化参数 init-param
        System.out.println(" 初始化参数 username  的值是;" + servletConfig.getInitParameter("username"));
        System.out.println(" 初始化参数 url  的值是;" + servletConfig.getInitParameter("url"));
        // 3 、获取 ServletContext 对象
        System.out.println(servletConfig.getServletContext());
    }

    // 要是实现类中获取xml中的初始化信息，则需要重写init方法

    // ServletConfig 用法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get...");
        // ServletConfig servletConfig = getServletConfig();
        // String username = servletConfig.getInitParameter("username");
        // String url = servletConfig.getInitParameter("url");
        // System.out.println(username);
        // System.out.println(url);
        // // 3 、获取 ServletContext 对象
        // System.out.println("ServletContext 对象:"+servletConfig.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post... ");
    }
}
