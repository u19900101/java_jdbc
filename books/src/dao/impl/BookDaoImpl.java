package dao.impl;

import dao.BaseDao;
import dao.BookDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Book;
import pojo.User;
import utils.DBUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @author lppppp
 * @create 2021-01-04 9:32
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    BaseDao baseDao = new BaseDao();
    @Override
    public int addBook(Book book) {
        Connection conn = DBUtils.getConn();
        String sql = "insert into t_book(name,author,price,sales,stock,img_path)values(?,?,?,?,?,?)";
        int update = baseDao.update(conn, sql,book.getName(),book.getAuthor(),book.getPrice()
                                    ,book.getSales(),book.getStock(),book.getImgPath());
        if(update>0){
            System.out.println(" addBook succeed ...");
        }
        DBUtils.closeResource(conn);
        return update;
    }

    @Override
    public int updateBookById(Book book) {
        Connection conn = DBUtils.getConn();
        String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id = ?";
        int update = baseDao.update(conn, sql,book.getName(),book.getAuthor(),book.getPrice()
                ,book.getSales(),book.getStock(),book.getImgPath(),book.getId());
        if(update>0){
            System.out.println(" updateBookById succeed ...");
        }
        DBUtils.closeResource(conn);
        return update;
    }

    @Override
    public int deleteBook(Book book) {
        Connection conn = DBUtils.getConn();
        String sql = "delete from  t_book  where id = ?";
        int update = baseDao.update(conn, sql,book.getId());
        if(update>0){
            System.out.println(" deleteBook succeed ...");
        }
        DBUtils.closeResource(conn);
        return update;

    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";
        Connection conn = DBUtils.getConn();
        Book book = baseDao.getInsance(conn, sql, new BeanHandler<>(Book.class), id);
        DBUtils.closeResource(conn);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        Connection conn = DBUtils.getConn();
        List<Book> books = baseDao.getInsanceList(conn, sql, new BeanListHandler<>(Book.class));
        DBUtils.closeResource(conn);
        return books;
    }


}
