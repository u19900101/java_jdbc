import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lppppp
 * @create 2020-12-31 10:05
 *
 * 1.给客户端回传数据
 * 2.解决中文乱码问题
 * 3.演示请求转发
 */
@WebServlet(name = "_6_ServletDispatcher1")
public class _6_ServletDispatcher1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("come into 111111111111111111 ...");
        System.out.println( response.getCharacterEncoding() );//默认ISO-8859-1

        /*方法1 */
        /*// 设置服务器字符集为UTF-8
        response.setCharacterEncoding("UTF-8");
        // 通过响应头，设置浏览器也使用UTF-8字符集
        response.setHeader("Content-Type", "text/html; charset=UTF-8");*/

        /*方法2 */
        // 它会同时设置服务器和客户端都使用UTF-8字符集，还设置了响应头
        // 此方法一定要在获取流对象之前调用才有效
        response.setContentType("text/html; charset=UTF-8");

//        System.out.println( resp.getCharacterEncoding() );

        // 两种流只能使用一个
        /*ServletOutputStream outputStream = response.getOutputStream();*/
        PrintWriter writer = response.getWriter();
        writer.write("中国！");
//        writer.write("CHINA!");

        String name = request.getParameter("name");
        request.setAttribute("balance",10000);
        System.out.println(name);
        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/_6_ServletDispatcher2");
        // 演示访问 web-inf下的信息
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/b.html");

        // 演示 无法访问别的工程文件 默认为http://localhost:8080/5_web + 跳转地址
        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("http://localhost:8080/4_web/");

        requestDispatcher.forward(request,response);
    }
}
