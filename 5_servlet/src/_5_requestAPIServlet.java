import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author lppppp
 * @create 2020-12-31 8:56
 * HttpServletRequest 各种方法
 * 1.
 */
@WebServlet(name = "_5_requestAPIServlet")
public class _5_requestAPIServlet extends HttpServlet {
    // 演示获取基本信息
    protected void doGet1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("welcome To HttpServletRequest...");
        String requestURI = req.getRequestURI(); // /5_web/h5
        System.out.println(requestURI); // http://localhost:8080/5_web/h5

       /* http://127.0.0.1:8080/5_web/h5
        http://192.168.43.251:8080/5_web/h5*/
        String requestURL = String.valueOf(req.getRequestURL());
        System.out.println(requestURL);

        String remoteHost = req.getRemoteHost(); // 0:0:0:0:0:0:0:1
        int remotePort = req.getRemotePort();  // 51659
        System.out.println(remoteHost + "------" + remotePort);
    }

    // 演示获取表单值
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showinfo(req);
    }

    //解决doPost 中文乱码问题
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        showinfo(req);
    }

    public void showinfo(HttpServletRequest req){

        String name = req.getParameter("username");
        String password = req.getParameter("password");
        // 获取数组时使用 values 方法
        String[] hobbies = req.getParameterValues("hobby");
        System.out.println(name);
        System.out.println(password);
        System.out.println(Arrays.asList(hobbies));
    }
}
