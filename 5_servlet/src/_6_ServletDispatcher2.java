import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2020-12-31 10:05
 */
public class _6_ServletDispatcher2 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("come into 222222222222222 ...");
        String name = request.getParameter("name");
        String balance = String.valueOf(request.getAttribute("balance"));

        System.out.println(name + "------------" + balance);
    }
}
