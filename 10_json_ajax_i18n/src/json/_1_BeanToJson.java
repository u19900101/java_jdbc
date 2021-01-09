package json;

import com.google.gson.Gson;
import org.junit.Test;
import pojo.Person;

/**
 * @author lppppp
 * @create 2021-01-09 20:20
 */
public class _1_BeanToJson {
    @Test
    public void T1(){
        Person jingjing = new Person(1, "jingjing");
        Gson gson = new Gson();
        String s = gson.toJson(jingjing);
        System.out.println(s);
    }
}
