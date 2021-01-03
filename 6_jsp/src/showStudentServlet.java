import k.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lppppp
 * @create 2021-01-01 20:23
 */
public class showStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i+1,"id"+i+1,i*2,"phone"+i));
        }
        students.forEach(System.out::println);

        req.setAttribute("studentList",students);
        req.getRequestDispatcher("/showStudentInfo.jsp").forward(req,resp);


    }
}
