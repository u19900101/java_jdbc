import javax.servlet.*;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2021-01-08 22:15
 */
public class Filter1 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("before Filter1____"+Thread.currentThread().getName());
        chain.doFilter(req, resp);
        System.out.println("after Filter1____"+Thread.currentThread().getName());
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
