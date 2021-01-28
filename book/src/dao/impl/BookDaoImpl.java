package dao.impl;

import dao.BaseDao;
import dao.BookDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
@Repository
@Transactional
public class BookDaoImpl extends BaseDao implements BookDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,author,price,sales,stock,img_path)values(?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql,book.getName(),book.getAuthor(),book.getPrice()
                                    ,book.getSales(),book.getStock(),book.getImgPath());
        if(update>0){
            System.out.println(" addBook succeed ...");
        }
        return update;
    }

    @Override
    public int updateBookById(Book book) {
        String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id = ?";
        int update = jdbcTemplate.update(sql,book.getName(),book.getAuthor(),book.getPrice()
                ,book.getSales(),book.getStock(),book.getImgPath(),book.getId());
        if(update>0){
            System.out.println(" updateBookById succeed ...");
        }
        return update;
    }

    @Override
    public int deleteBook(Integer id) {
        String sql = "delete from  t_book  where id = ?";
        int update = jdbcTemplate.update(sql,id);
        if(update>0){
            System.out.println(" deleteBook succeed ...");
        }
        return update;

    }

    @Override
    public Book queryBookById(Integer id) {
        Book book=null;
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";
        try {
            book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return book;
        }
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        List<Book> bookList = null;
        try {
            bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return bookList;
        }
        // Connection conn = DBUtils.getConn();
        // List<Book> books = getInsanceList(conn, sql, new BeanListHandler<>(Book.class));
        // return books;
    }

    @Override
    public int getSingleValue() {
        String sql = "select count(*) from t_book";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        // long singleValue = (Long) getSingleValue(conn, sql);
        return integer;
    }


    @Override
    public List<Book> getPageList(int begin, int size) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        List<Book> bookList =null;
        try {
            bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),begin,size);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return bookList;
        }
    }

    @Override
    public int getCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class,min,max);
        return integer;
    }

    @Override
    public List<Book> getPageListByPrice(int min, int max, int begin, int size) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from " +
                "t_book where price between ? and ? order by price limit ?,?";
        List<Book> bookList =null;
        try {
            bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),min,max,begin,size);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return bookList;
        }
    }
}
