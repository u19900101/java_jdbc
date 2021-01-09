package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import pojo.Person;
import pojo.PersonType;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        // 将json转换为person
        Person person = gson.fromJson(s, Person.class);
        System.out.println(person);
    }

    /* json 和 list 之间的相互转换*/
    @Test
    public void T2(){
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person(1,"name1"));
        personArrayList.add(new Person(2,"name2"));
        personArrayList.add(new Person(3,"name3"));

        Gson gson = new Gson();
        String s = gson.toJson(personArrayList);
        System.out.println(s);

        // ArrayList<Person> personArrayList1= gson.fromJson(s, new PersonType().getType());
        ArrayList<Person> personArrayList1= gson.fromJson(s, new TypeToken<ArrayList<Person>>(){}.getType());
        System.out.println(personArrayList1);
        System.out.println(personArrayList1.get(0).getClass());
    }

    @Test
    public void TtoMap(){
        HashMap<Integer, Person> hashMap = new HashMap<>();
        hashMap.put(1, new Person(1,"k1"));
        hashMap.put(2, new Person(2,"k2"));
        hashMap.put(3, new Person(3,"k3"));

        Gson gson = new Gson();
        String s = gson.toJson(hashMap);
        System.out.println(s);

        HashMap<Integer, Person> json = gson.fromJson(s, new TypeToken<HashMap<Integer, Person>>() {
        }.getType());
        System.out.println(json);

    }
}
