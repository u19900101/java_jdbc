package i18n;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author lppppp
 * @create 2021-01-10 14:45
 */
public class demo_i18n {
    @Test
    public void T(){
        Locale aDefault = Locale.getDefault();
        System.out.println(aDefault);
        System.out.println(Locale.CHINA);
    }

    @Test
    public void T2(){
        Locale locale = Locale.CHINA;
        ResourceBundle bundle = ResourceBundle.getBundle("i18n",locale);
        System.out.println(bundle.getString("username"));
        System.out.println();
        locale = Locale.US;
        bundle = ResourceBundle.getBundle("i18n",locale);
        System.out.println(bundle.getString("username"));
    }
}
