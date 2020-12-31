import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2020-12-30 22:34
 */
@WebServlet(name = "_4_ServletContext_Map")
public class _4_ServletContext_Map extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("key","value");

        Object key = servletContext.getAttribute("key");
        System.out.println(key);
    }
}
