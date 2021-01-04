package service;

import pojo.Book;

import java.util.List;

/**
 * @author lppppp
 * @create 2021-01-04 11:18
 */
public interface BookService {

    public int addBook(Book book);
    public int updateBookById(Book book);
    public int deleteBookById(Integer id);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
}