import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lppppp
 * @create 2020-12-30 8:46
 */
public class _2_dom4j {
    //加载 xml 文件
    @Test
    public void T1() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read("book.xml");
        System.out.println(read);
    }

    // 读取xml 文件 到 Book对象中
    @Test
    public void T2() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read("book.xml");
        Element rootElement = read.getRootElement();
        List<Element> books = rootElement.elements();
        for(Element book :books){
            String name = book.elementText("name");
            BigDecimal price = new BigDecimal(book.elementText("price"));
            String author = book.elementText("author");
            String id = book.attribute("id").getValue();
            Book book1 = new Book(id, name, author, price);
            System.out.println(book1);
        }
    }

}
