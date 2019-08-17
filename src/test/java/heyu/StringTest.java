package heyu;

/**
 * @author heyu
 * @date 2019/5/20
 */
public class StringTest {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    private static ThreadLocal<Long> threadLocal2 = new ThreadLocal<>();

	public static void main(String[] args) {
	    new Thread(StringTest::stringBuilderTest).start();
        new Thread(StringTest::stringBufferTest).start();
        new Thread(StringTest::stringTest).start();
	}

	private static void stringBuilderTest() {
        long a = System.currentTimeMillis();
        threadLocal.set(a);
		StringBuilder xxx = new StringBuilder("xxx");
		for (int i = 0; i < 100000000; i++) {
			xxx.append("a");
		}
		long b = System.currentTimeMillis();
        threadLocal2.set(b);
		System.out.println("StringBuilder " + (threadLocal2.get() - threadLocal.get()));
	}

	private static void stringBufferTest() {
        long a = System.currentTimeMillis();
        threadLocal.set(a);
		StringBuffer xxx = new StringBuffer("xxx");
		for (int i = 0; i < 100000000; i++) {
			xxx.append("a");
		}
        long b = System.currentTimeMillis();
        threadLocal2.set(b);
        System.out.println("StringBuffer  " + (threadLocal2.get() - threadLocal.get()));
	}

	private static void stringTest() {
        long a = System.currentTimeMillis();
        threadLocal.set(a);
		String xxx = "xxx";
		for (int i = 0; i < 100000; i++) {
			xxx = xxx + "a";
		}
        long b = System.currentTimeMillis();
        threadLocal2.set(b);
        System.out.println("String        " + (threadLocal2.get() - threadLocal.get()));
	}

	class Axf{

	}
}
