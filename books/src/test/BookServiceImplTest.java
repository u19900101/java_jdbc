package test;

import dao.impl.BookDaoImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojo.Book;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;
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
    public void addBook() {
        bookService.addBook(book);
    }

    @Test
    public void updateBookById() {
        bookService.updateBookById(book);
    }

    @Test
    public void deleteBook() {

        int i = bookService.deleteBookById(21);
        System.out.println(i);
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(10);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        books.forEach(System.out::println);
    }
}
