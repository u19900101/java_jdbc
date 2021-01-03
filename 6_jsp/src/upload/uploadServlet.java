package upload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lppppp
 * @create 2021-01-01 20:23
 */
public class uploadServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //    1.判断是否为多段文件
        if(ServletFileUpload.isMultipartContent(req)){
            // 创建FileItemFactory工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类ServletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            // 解析上传的数据，得到每一个表单项FileItem
            List<FileItem> list = null;
            try {
                list = servletFileUpload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            // 循环判断，每一个表单项，是普通类型，还是上传的文件
            for (FileItem fileItem : list) {

                if (fileItem.isFormField()) {
                    // 普通表单项
                    System.out.println("表单项的name属性值：" + fileItem.getFieldName());
                    // 参数UTF-8.解决乱码问题
                    System.out.println("表单项的value属性值：" + fileItem.getString("UTF-8"));

                } else {
                    // 上传的文件
                    System.out.println("表单项的name属性值：" + fileItem.getFieldName());
                    System.out.println("上传的文件名：" + fileItem.getName());

                    try {
                        // fileItem.write(new File("D:\\MyJava\\4_javaweb\\6_jsp\\uploadFiles\\"+fileItem.getName()));
                        fileItem.write(new File("uploadFiles\\"+fileItem.getName()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }


    //测试文件路径问题  默认路径为当前的module下   前面不加 ./
    // D:\MyJava\4_javaweb\6_jsp\
    @Test
    public void T(){
        File file = new File("1.txt");
        /*boolean newFile = false;
        try {
            newFile = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        System.out.println(file.getAbsoluteFile());

    }
    // 演示 获取原始信息
    /*public void doPost1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("come in uploadServlet please ...");
        String username = req.getParameter("username");
        String photo = req.getParameter("photo");
        System.out.println(username);
        System.out.println(photo);

        ServletInputStream inputStream = req.getInputStream();
        byte []bytes = new byte[10001024];
        int read = inputStream.read(bytes);

        System.out.println(new String(bytes,0,read));
        inputStream.close();
    }*/
}
