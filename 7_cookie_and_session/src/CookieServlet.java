import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2021-01-06 16:55
 */

public class CookieServlet extends BaseServlet {
    public void createCookie(HttpServletRequest req,HttpServletResponse res) throws IOException {
        Cookie cookie1 = new Cookie("key1","value1");
        res.addCookie(cookie1);

        Cookie cookie2 = new Cookie("key2","value2");
        res.addCookie(cookie2);

        res.getWriter().write("创建鸟cookie");
    }

    public void getCookie(HttpServletRequest req,HttpServletResponse res) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            res.getWriter().write("Cookie["+cookie.getName()+"] = "+cookie.getValue()+"<br/>");
        }
    }

    public void defaultLife(HttpServletRequest req,HttpServletResponse res) throws IOException {
        Cookie  cookie  = new Cookie("defaultLife","defaultLife");
        cookie.setMaxAge(-1);
        res.addCookie(cookie);
    }

    public void defaultNow(HttpServletRequest req,HttpServletResponse res) throws IOException {
        Cookie  cookie  = new Cookie("defaultLife_now","defaultLife_now");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

    public void life3600(HttpServletRequest req,HttpServletResponse res) throws IOException {
        Cookie  cookie  = new Cookie("life3600","life3600");
        cookie.setMaxAge(3600);
        res.addCookie(cookie);
    }


    public void testPath(HttpServletRequest req,HttpServletResponse res) throws IOException {
        Cookie  cookie1  = new Cookie("testPath1","testPath1");
        cookie1.setPath(req.getContextPath());
        System.out.println("before");
        System.out.println(req.getContextPath());
        System.out.println("end");

        res.addCookie(cookie1);
        Cookie  cookie2  = new Cookie("testPath2","testPath2");
        cookie2.setPath(req.getContextPath()+"/abc/cookie.html");
        res.addCookie(cookie2);
    }
}
