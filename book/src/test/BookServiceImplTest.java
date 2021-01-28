package test;

import dao.impl.BookDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojo.Book;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lppppp
 * @create 2021-01-04 11:25
 */

public class BookServiceImplTest {

    BookServiceImpl bookService = new BookServiceImpl();

    Book book = new Book(20,"pppppp人月神话" , "刚哥" ,
            new BigDecimal(88.15) , 20 , 80 , "static/img/default.jpg");
    @Test
    public void addBook() throws SQLException {
        bookService.addBook(book);
    }

    @Test
    public void updateBookById() throws SQLException {
        bookService.updateBookById(book);
    }

    @Test
    public void deleteBook() throws SQLException {

        int i = bookService.deleteBookById(21);
        System.out.println(i);
    }

    @Test
    public void queryBookById() throws SQLException {
        Book book = bookService.queryBookById(10);
        System.out.println(book);
    }

    @Test
    public void queryBooks() throws SQLException {
        List<Book> books = bookService.queryBooks();
        books.forEach(System.out::println);
    }
}
