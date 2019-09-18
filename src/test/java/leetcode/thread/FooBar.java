package leetcode.thread;

import java.util.concurrent.Semaphore;

/**
 * @author heyu
 * @date 2019/9/18
 */
public class FooBar {

    private Semaphore foo = new Semaphore(1);
    private Semaphore bar = new Semaphore(0);

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
            // printBar.run() outputs "bar". Do not change or remove this line.
        }
    }
}
