package leetcode.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author heyu
 * @date 2019/9/18
 */
public class ThreadTest implements Runnable{

	private String value;

	public ThreadTest(){
		lock = new ReentrantLock();
		condition2 = lock.newCondition();
		condition3 = lock.newCondition();
	}

    public ThreadTest(String value)  {
        this.value = value;
    }
	private Lock lock;
	private Condition condition2;
	private Condition condition3;

	private boolean s = true;
	private boolean t = true;

	public void first(Runnable printFirst) throws InterruptedException {
		lock.lockInterruptibly();
		// printFirst.run() outputs "first". Do not change or remove this line.
		try {
			printFirst.run();
			if (s){
				condition2.signalAll();
				s = false;
			}
		} finally {
			lock.unlock();
		}
	}

	public void second(Runnable printSecond) throws InterruptedException {
		// printSecond.run() outputs "second". Do not change or remove this line.
		lock.lockInterruptibly();
		try {
			if (s){
				condition2.await();
			}
			printSecond.run();
			if (t){
				condition3.signalAll();
				t = false;
			}
		} finally {
			lock.unlock();
		}
	}

	public void third(Runnable printThird) throws InterruptedException {
		// printThird.run() outputs "third". Do not change or remove this line.
		lock.lockInterruptibly();
		try {
			if (t){
				condition3.await();
			}
			printThird.run();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		System.out.println(value);
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadTest threadTest = new ThreadTest("1");
		ThreadTest threadTest2 = new ThreadTest("2");
		ThreadTest threadTest3 = new ThreadTest("3");
		ThreadTest threadTest1 = new ThreadTest();
		new Thread(() -> {
			try {
				threadTest1.second(threadTest2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		threadTest1.first(threadTest);
		threadTest1.third(threadTest3);
	}
}
