import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author lppppp
 * @create 2021-01-01 22:12
 */
public class Listener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("init ...");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("destroy ... ");
    }
}
