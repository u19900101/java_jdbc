import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2021-01-08 20:25
 */
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init ...");
        System.out.println("2.Filter 的 的 init(FilterConfig filterConfig) 初始化");
        // 1 、获取 Filter 的名称 filter-name 的内容
        System.out.println("filter-name  的值是：" + filterConfig.getFilterName());
        // 2 、获取在 web.xml 中配置的 init-param 初始化参数
        System.out.println(" 初始化参数 username  的值是 ：" + filterConfig.getInitParameter("username"));
        System.out.println(" 初始化参数 url  的值是：" + filterConfig.getInitParameter("url"));
        // 3 、获取 ServletContext 对象
        System.out.println(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    //1.判断 session域中 是否有 登录的user属性
        System.out.println("come into doFilter ...");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Object user = req.getSession().getAttribute("user");
        if(user == null){
           // 2.未登录，跳转到登录页面
            req.getRequestDispatcher("/index.jsp").forward(req, servletResponse);
            return;
        }else {
           // 3.已登录，正常进行下步操作
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy ...");
    }
}
