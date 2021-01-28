package filter;

import utils.DBUtils;
import utils.WebUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2021-01-09 11:09
 */
public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(req, resp);
            DBUtils.commitAndcloseResource();
        } catch (ServletException e) {
            e.printStackTrace();
            DBUtils.rollbackAndcloseResource();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
