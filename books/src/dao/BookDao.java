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
    public int getSingleValue();
    List<Book> getPageList(int begin, int size);

    int getCountByPrice(int min, int max);

    List<Book> getPageListByPrice(int min, int max, int begin, int size);
}
