package heyu.complteservice;

import java.util.concurrent.*;

/**
 * @author heyu
 * @date 2019/5/9
 */
public class CompletionServiceTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
	    BlockingQueue<Future<Integer>> blockingQueue = new ArrayBlockingQueue<>(20);
		// 创建线程池
		ExecutorService executor = Executors.newFixedThreadPool(3);
		// 创建 CompletionService
		CompletionService<Integer> cs = new ExecutorCompletionService<>(executor, blockingQueue);
		// 异步向电商 S1 询价
		cs.submit(() -> getPriceByS1(5));
		// 异步向电商 S2 询价
		cs.submit(() -> getPriceByS1(3));
		// 异步向电商 S3 询价
		cs.submit(() -> getPriceByS1(1));
		// 将询价结果异步保存到数据库
		for (int i = 0; i < 3; i++) {
			Integer r = blockingQueue.take().get();
			System.out.println(r);
		}

	}

	private static Integer getPriceByS1(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
	    return seconds;
    }
}
