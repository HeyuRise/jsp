package heyu.futuretask;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class HeyuTest implements Runnable {

	private static LinkedBlockingQueue a = new LinkedBlockingQueue(10);
	private static LinkedBlockingQueue b = new LinkedBlockingQueue(10);

	private static LongAdder aa = new LongAdder();
	private static LongAdder bb = new LongAdder();
	private static CyclicBarrier barrier = new CyclicBarrier(2, new Cyc(a, b));

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// for (int i = 0; i < 10; i++)
		// checkAll(barrier);
		future();
		System.out.println("xxxxxxxx");
	}

	private static void future() throws ExecutionException, InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		// 创建任务 T2 的 FutureTask
		FutureTask<String> ft2 = new FutureTask<>(new T2Task());
		// 创建任务 T1 的 FutureTask
		FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));
		es.submit(ft1);
		es.submit(ft2);
		// 等待线程 T1 执行结果
		System.out.println(ft1.get());

	}

	private static void checkAll(CyclicBarrier barrier) {
		System.out.println("aaaaaaaaaaa");
		// 循环查询订单库
		Thread T1 = new Thread(() -> {
			aa.increment();
			// 查询订单库
			a.add(aa.longValue());
			// 等待
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		});
		T1.start();
		// 循环查询运单库
		Thread T2 = new Thread(() -> {
			bb.increment();
			// 查询运单库
			b.add(bb.longValue());
			// 等待
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		});
		System.out.println("bbbbbbbbbbbbbb");
		T2.start();
	}

	private static void finallyTest() {
		System.out.println("a" + a.peek());
		System.out.println("b" + b.peek());

	}

	@Override
	public void run() {
		finallyTest();
	}

	// T1Task 需要执行的任务：
	// 洗水壶、烧开水、泡茶
	static class T1Task implements Callable<String> {
		FutureTask<String> ft2;

		// T1 任务需要 T2 任务的 FutureTask
		T1Task(FutureTask<String> ft2) {
			this.ft2 = ft2;
		}

		@Override
		public String call() throws Exception {
			System.out.println("T1: 洗水壶...");
			TimeUnit.SECONDS.sleep(1);

			System.out.println("T1: 烧开水...");
			TimeUnit.SECONDS.sleep(15);
			// 获取 T2 线程的茶叶
			String tf = ft2.get();
			System.out.println("T1: 拿到茶叶:" + tf);

			System.out.println("T1: 泡茶...");
			return " 上茶:" + tf;
		}
	}

	// T2Task 需要执行的任务:
	// 洗茶壶、洗茶杯、拿茶叶
	static class T2Task implements Callable<String> {
		@Override
		public String call() throws Exception {
			System.out.println("T2: 洗茶壶...");
			TimeUnit.SECONDS.sleep(1);

			System.out.println("T2: 洗茶杯...");
			TimeUnit.SECONDS.sleep(2);

			System.out.println("T2: 拿茶叶...");
			TimeUnit.SECONDS.sleep(1);
			return " 龙井 ";
		}
	}
}
