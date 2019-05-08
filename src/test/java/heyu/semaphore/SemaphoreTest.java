package heyu.semaphore;

/**
 * @author heyu
 * @date 2019/5/5
 */
public class SemaphoreTest {

	public static void main(String[] args) throws InterruptedException {
		ObjPool<Integer, String> objPool = new ObjPool<>(2, 100);
		for (int i = 0; i < 5; i++) {
			System.out.println( objPool.exec(s -> {
				System.out.println(s);
				return null;
			}));
		}
	}
}
