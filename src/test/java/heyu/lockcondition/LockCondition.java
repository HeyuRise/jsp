package heyu.lockcondition;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author heyu
 * @date 2019/5/5
 */
public class LockCondition {

    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("out1");
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i < 20; i++){
            new Thread(() -> {
                try {
                    blockingQueue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("in");
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("out2");
            }).start();
        }
    }
}
