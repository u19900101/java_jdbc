import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author lppppp
 * @create 2021-01-01 9:26
 */
public class printServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //org.apache.jasper.runtime.HttpJspBase  httpJspBase;
        PrintWriter writer = res.getWriter();
        System.out.println("I am do Get");
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"GBK\">\n" +
                "    <title>Goodbye 2020</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1> Hello 2021 </h1>\n" +
                "<h1> 你好 2021 </h1>\n" +
                "</body>\n" +
                "</html>";
        writer.write(html);
    }
}
