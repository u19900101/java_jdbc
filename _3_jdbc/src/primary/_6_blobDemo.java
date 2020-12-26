package primary;

import org.junit.jupiter.api.Test;
import util.JDBCUtil;

import java.io.*;
import java.sql.*;

/**
 * @author lppppp
 * @create 2020-12-25 22:09
 *
 * 1.向数据库中传入 图片文件
 * 2.从数据库中下载图片到本地
 */
public class _6_blobDemo {
    @Test
    public void insertDemo() throws  Exception {
        Connection conn = JDBCUtil.getConn();
        String sql = "insert into customers(name,email,birth,photo)" +
                "values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,"姚明");
        ps.setObject(2,"168@qq.com");
        ps.setObject(3,"1990-01-01");
        InputStream is = new FileInputStream("gofree.jpg");
        ps.setObject(4,is);
        ps.execute();
        JDBCUtil.closeResource(ps,conn);
    }

    @Test
    public void updateDemo() throws  Exception {
        Connection conn = JDBCUtil.getConn();
        String sql = "update customers set photo = ?,name = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        InputStream is = new FileInputStream("gofree.jpg");
        ps.setObject(1,is);
        ps.setObject(2,"lppppp");
        ps.setObject(3,22);
        ps.execute();
        JDBCUtil.closeResource(ps,conn);
    }

    @Test
    public void Tpath() throws FileNotFoundException {
        InputStream is = new FileInputStream("kk.png");
        System.out.println(is);
    }

    @Test
    public void T2(){
        InputStream is = null;
        FileOutputStream os = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = JDBCUtil.getConn();
            String sql = "select name,email,birth,photo from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,16);
            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");
                Customer customer = new Customer(16, name, email, birth);
                System.out.println(customer);

                // 将图片写进本地文件进行保存
                Blob photo = resultSet.getBlob("photo");
                is = photo.getBinaryStream();
                os = new FileOutputStream("m.jpg");
                byte []bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes)) != -1){
                    os.write(bytes,0,len);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(ps,conn);
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

