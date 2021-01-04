package service.impl;

import dao.impl.BookDaoImpl;
import pojo.Book;
import service.BookService;
import java.util.List;

/**
 * @author lppppp
 * @create 2021-01-04 11:19
 */
public class BookServiceImpl implements BookService {
    BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int updateBookById(Book book) {
        return bookDao.updateBookById(book);
    }

    @Override
    public int deleteBookById(Integer id) {
        return bookDao.deleteBook(id);
    }


    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}
