package dao;

import pojo.Book;
import pojo.User;

import java.util.List;

/**
 * @author lppppp
 * @create 2020-12-31 19:30
 */
public interface BookDao {
    public int addBook(Book book);
    public int updateBookById(Book book);
    public int deleteBook(Integer id);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
}
