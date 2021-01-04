package utils;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lppppp
 * @create 2021-01-04 8:38
 */
public class WebUtils {

    public static <T>T copyBean(Map map, T t){

        try {
            BeanUtils.populate(t,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(t);
        return t;
    }


}
