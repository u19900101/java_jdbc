/**
 * @author lppppp
 * @create 2021-01-09 9:20
 */
public class Work2 {
    public static void work(){
        System.out.println("Work2__"+Thread.currentThread().getName()+"___Num is __"+Task.threadLocal.get());
    }
}
