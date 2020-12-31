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
public class _2_httpServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
//        super.init();
        System.out.println("get into init ....");
    }

    // 要是实现类中获取xml中的初始化信息，则需要重写init方法

    // ServletConfig 用法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get...");
        ServletConfig servletConfig = getServletConfig();
        String username = servletConfig.getInitParameter("username");
        String url = servletConfig.getInitParameter("url");
        System.out.println(username);
        System.out.println(url);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post... ");
    }
}
