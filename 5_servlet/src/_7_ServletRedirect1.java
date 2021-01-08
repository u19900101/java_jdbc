import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2020-12-31 15:36
 *
 * 演示重定向
 */
public class _7_ServletRedirect1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("come into _7_ServletRedirect1 ....");
        // 不能获取request域中的值
        request.setAttribute("key","value");
        /*方式1*/
        response.setStatus(302);
//        response.setHeader("Location","http://localhost:8080/5_web/h72");
        //不能访问 WEB-INF下的文件
        //response.setHeader("Location","http://localhost:8080/5_web/WEB-INF/b.html");

        /*方式2*/
        response.sendRedirect("http://localhost:8080/5_web/h72");

    }
}
