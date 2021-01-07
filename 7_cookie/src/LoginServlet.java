import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2021-01-07 8:48
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");

        System.out.println("come into cookie");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Cookie[] cookies = req.getCookies();
        boolean flag = true;
        for (Cookie cookie : cookies) {
            if("username".equalsIgnoreCase(cookie.getName())){
                res.getWriter().write("<h1>免登陆哦</h1>");
                System.out.println(cookie.getName() + "  "+cookie.getValue());
                flag = false;
                break;
            }
        }
        if(flag&&"kk".equalsIgnoreCase(username)&&"kk".equalsIgnoreCase(password)){
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60*60*24*7);
            res.addCookie(cookie);
            System.out.println("cookie 设置成功");
            res.getWriter().write("<h1>cookie 设置成功,下次免登陆哦</h1>");
        }
    }
}
