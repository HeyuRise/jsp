package leetcode.thread;

import java.util.concurrent.Semaphore;

/**
 * @author heyu
 * @date 2019/9/19
 */
public class ZeroEvenOdd {

    private Semaphore semaphore0 = new Semaphore(1);
    private Semaphore semaphoreEven = new Semaphore(0);
    private Semaphore semaphoreOdd = new Semaphore(0);

	private int n;

	public ZeroEvenOdd(int n) {
		this.n = n;
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void zero(IntConsumer printNumber) throws InterruptedException {
		for (int i = 1; i <= n; i++) {
		    semaphore0.acquire();
            printNumber.accept(0);
            if (i % 2 == 1) {
                semaphoreOdd.release();
            } else {
                semaphoreEven.release();
            }
		}
	}

	public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i = i+2) {
            semaphoreEven.acquire();
            printNumber.accept(i);
            semaphore0.release();
		}
	}

	public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i = i+2) {
            semaphoreOdd.acquire();
            printNumber.accept(i);
            semaphore0.release();
		}
	}

	static class IntConsumer {

		public void accept(int x) {
			System.out.print(x);
		}

	}

    public static void main(String[] args) {
        IntConsumer intConsumer = new IntConsumer();
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(10);
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
