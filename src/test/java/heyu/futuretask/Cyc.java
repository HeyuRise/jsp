package heyu.futuretask;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author heyu
 * @date 2019/4/22
 */
public class Cyc implements Runnable {

    private LinkedBlockingQueue a = new LinkedBlockingQueue(10);
    private LinkedBlockingQueue b = new LinkedBlockingQueue(10);

    public Cyc(LinkedBlockingQueue a, LinkedBlockingQueue b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a" + a.poll());
        System.out.println("b" + b.poll());
    }
}
