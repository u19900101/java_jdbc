import java.util.Random;

/**
 * @author lppppp
 * @create 2021-01-09 9:09
 */
public class Task implements Runnable {
    static Random random = new Random();
    public static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

    @Override
    public void run() {
        int i = random.nextInt(1000);
        threadLocal.set(i);
        System.out.println(Thread.currentThread().getName()+"___Num is __"+i);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Work1.work();
        System.out.println("after moment "+Thread.currentThread().getName()+"___Num is __"+threadLocal.get());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(new Task()).start();
        }
    }
}
