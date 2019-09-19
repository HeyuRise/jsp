package leetcode.thread;

import java.util.concurrent.Semaphore;

/**
 * @author heyu
 * @date 2019/9/19
 */
public class H2O {

	private Semaphore semaphoreH = new Semaphore(4);
	private Semaphore semaphoreO = new Semaphore(2);

	public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		semaphoreH.acquire(2);
		releaseHydrogen.run();
		semaphoreO.release();
	}

	public void oxygen(Runnable releaseOxygen) throws InterruptedException {
		semaphoreO.acquire(2);
		releaseOxygen.run();
		semaphoreH.release(4);
	}

	public static void main(String[] args) {
		H2O h2O = new H2O();
		for (int i = 0; i < 20; i++) {
			new Thread(() -> {
				try {
					h2O.oxygen(() -> System.out.print("O"));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
		for (int i = 0; i < 40; i++) {
			new Thread(() -> {
				try {
					h2O.hydrogen(() -> System.out.print("H"));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
