package test;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lppppp
 * @create 2021-01-03 23:00
 */
public class _3_reflect {

    public void login(){
        System.out.println("login executed...");
    }
    public void update(){
        System.out.println("update executed...");
    }
    public void delete(){
        System.out.println("delete executed...");
    }
    public void select(){
        System.out.println("select executed...");
    }

    public static void main(String[] args) throws Exception{
        String []methods = {"login","update","delete","select"};
        for (String s:methods) {
            Method method = _3_reflect.class.getDeclaredMethod(s);
            method.invoke(new _3_reflect());
        }
    }
}
