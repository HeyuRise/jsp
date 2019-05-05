package heyu.completablefuture;

import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author heyu
 * @date 2019/4/26
 */
public class A {



	private static ExecutorService executorService = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// a();
		test();
	}

	public static void b() {
		CompletableFuture<String> f0 = CompletableFuture.supplyAsync(() -> "Hello World") // ①
				.thenApply(s -> s + " QQ") // ②
				.thenApply(String::toUpperCase);// ③

		System.out.println(f0.join());
	}

	public static void a() {
		String name = "龙井";
		// 任务 1：洗水壶 -> 烧开水
		CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
			System.out.println(Thread.currentThread());
			System.out.println("T1: 洗水壶...");
			sleep(1, TimeUnit.SECONDS);

			System.out.println("T1: 烧开水...");
			sleep(10, TimeUnit.SECONDS);
		}, executorService);
		// 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread());
			System.out.println("T2: 洗茶壶...");
			sleep(1, TimeUnit.SECONDS);

			System.out.println("T2: 洗茶杯...");
			sleep(2, TimeUnit.SECONDS);

			System.out.println("T2: 拿茶叶...");
			sleep(3, TimeUnit.SECONDS);
			return name;
		}, executorService);
		// 任务 3：任务 1 和任务 2 完成后执行：泡茶
		CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
			System.out.println(Thread.currentThread());
			System.out.println("T1: 拿到茶叶:" + tf);
			System.out.println("T1: 泡茶...");
			return " 上茶:" + tf;
		});
		// 等待任务 3 执行结果
		System.out.println(f3.join());
		System.out.println(Thread.currentThread());

	}

	private static void test() throws ExecutionException, InterruptedException {
		String xx = "aaa";

		String name = "heyu";

		CompletableFuture<Void> f1 = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread());
				System.out.println(xx);
				System.out.println("runAsync");
			}
		}, executorService);

		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(new Supplier<String>() {
			@Override
			public String get() {
				System.out.println(Thread.currentThread());
				return name;
			}
		}, executorService);

		CompletableFuture<String> f3 = f1.thenCombine(f2, new BiFunction<Void, String, String>() {
			@Override
			public String apply(Void aVoid, String s) {
				System.out.println(Thread.currentThread());
				return s;
			}
		});

		System.out.println(Thread.currentThread());
		System.out.println(f3.join());

	}

	static void sleep(int t, TimeUnit u) {
		try {
			u.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
