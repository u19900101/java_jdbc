import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2020-12-30 15:51
 */
public class _1_ServletDemo implements Servlet {
    public _1_ServletDemo() {
        System.out.println("1.come into Constructor...");
    }

    // 1.获取servlet的别名
    // 2.获取初始化参数

    // ServletConfig 用法
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2.come into init...");
        String servletName = servletConfig.getServletName();
        System.out.println(servletName);
        String username = servletConfig.getInitParameter("username");
        String url = servletConfig.getInitParameter("url");
        System.out.println(username);
        System.out.println(url);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        if("GET".equals(method)){
            System.out.println("Excute GET request");
        }else if ("POST".equals(method)){
            System.out.println("Excute POST request");
        }
        System.out.println("3.welcome to servlet my old friend");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4.come into destroy...");
    }
}
