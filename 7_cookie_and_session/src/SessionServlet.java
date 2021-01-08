import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lppppp
 * @create 2021-01-07 10:46
 */
public class SessionServlet extends BaseServlet{

    protected void createOrGetSession(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 创建和获取Session会话对象
        HttpSession session = req.getSession();
        // 判断 当前Session会话，是否是新创建出来的
        boolean isNew = session.isNew();
        // 获取Session会话的唯一标识 id
        String id = session.getId();

        res.getWriter().write("得到的Session，它的id是：" + id + " <br /> ");
        res.getWriter().write("这个Session是否是新创建的：" + isNew + " <br /> ");

    }

    protected void setAttribute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 创建和获取Session会话对象
        HttpSession session = req.getSession();
        session.setAttribute("k1","v1");
        res.getWriter().write("已设置k1 = v1： <br /> ");
    }
    protected void getAttribute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 创建和获取Session会话对象
        HttpSession session = req.getSession();
        Object k1 = session.getAttribute("k1");
        System.out.println(k1);
        res.getWriter().write("已获取k1 = "+k1);
    }

    protected void defaultLife(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 创建和获取Session会话对象
        HttpSession session = req.getSession();
        int maxInactiveInterval = session.getMaxInactiveInterval();
        res.getWriter().write("默认时长为 "+maxInactiveInterval+"秒！");
    }

    protected void life3(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(3);
        res.getWriter().write("设置了3s超时 ");
    }
}
