import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2020-12-31 15:36
 */

public class _7_ServletRedirect2 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("2222222222222222222");
        Object key = request.getAttribute("key");
        System.out.println(key);
    }
}
