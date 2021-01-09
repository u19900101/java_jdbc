/**
 * @author lppppp
 * @create 2021-01-09 9:18
 */
public class Work1 {
    public static void work(){
        System.out.println("Work1__"+Thread.currentThread().getName()+"___Num is __"+Task.threadLocal.get());
        Work2.work();
    }
}
