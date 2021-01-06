package service.impl;

import dao.impl.BookDaoImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;
import java.util.List;

import static pojo.Page.PAGE_SIZE;

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

    @Override
    public int getSingleValue() {
        return bookDao.getSingleValue();
    }

    @Override
    public Page<Book> getPageList(int pageNo) {

        // 总记录数
        int pageTotalCount = getSingleValue();
        // 总页面数
        int pageTotal = pageTotalCount/ PAGE_SIZE;
        if(pageTotalCount% PAGE_SIZE != 0){
            pageTotal++;
        }

        int begin = (pageNo-1)* PAGE_SIZE;
        int size = PAGE_SIZE;
        // 判断是否为最后一页
        if(pageNo>=pageTotal){
            size = pageTotalCount-(pageTotal-1)*PAGE_SIZE;
        }
        // 当前页数据

        List<Book> bookList = bookDao.getPageList(begin, size);
        Page<Book> bookPage = new Page<>();
        bookPage.setItems(bookList);
        bookPage.setPageTotal(pageTotal);
        bookPage.setPageTotalCount(pageTotalCount);
        bookPage.setPageNo(pageNo);
        return bookPage;
    }
}
