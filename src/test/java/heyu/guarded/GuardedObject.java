package heyu.guarded;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author heyu
 * @date 2019/5/20
 */
public class GuardedObject<T> {

	// 受保护的对象
	private T obj;
	private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();
    private static final int timeout = 3;

	// 保存所有 GuardedObject
    private final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

    /**
     * 静态方法创建 GuardedObject
     *
     * @param key
     * @param <K>
     * @return
     */
	public static <K> GuardedObject create(K key) {
		GuardedObject go = new GuardedObject();
		gos.put(key, go);
		return go;
	}

	static <K, T> void fireEvent(K key, T obj) {
		GuardedObject go = gos.remove(key);
		if (go != null) {
			go.onChanged(obj);
		}
	}

    /**
     * 获取受保护对象
     *
     * @param p
     * @return
     */
	T get(Predicate<T> p) {
		lock.lock();
		try {
			// MESA 管程推荐写法
			if (!p.test(obj)) {
			    done.await();
				// done.await(timeout, TimeUnit.SECONDS);
			}
            if (!p.test(obj)) {
                return null;
            }
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			lock.unlock();
		}
		// 返回非空的受保护对象
		return obj;
	}

    /**
     * 事件通知方法
     *
     * @param obj
     */
    private void onChanged(T obj) {
		lock.lock();
		try {
			this.obj = obj;
			done.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
