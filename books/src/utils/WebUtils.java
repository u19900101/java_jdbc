package utils;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lppppp
 * @create 2021-01-04 8:38
 */
public class WebUtils {

    public static <T>T copyBean(HttpServletRequest req,T t){

        try {
            BeanUtils.populate(t,req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(t);
        return t;
    }


}
