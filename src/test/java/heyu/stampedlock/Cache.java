package heyu.stampedlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

/**
 * @author heyu
 * @date 2019/5/5
 */
public class Cache<K, V> {

	final Map<K, V> m = new HashMap<>();

	final StampedLock lock = new StampedLock();

	public V getOpt(K k) {
		if (k == null) {
			throw new NullPointerException();
		}
		long stamp = lock.tryOptimisticRead();
		V v = m.get(k);
		if (lock.validate(stamp)) {
			return v;
		}
		return get(k);
	}

	public V get(K k) {
		if (k == null) {
			throw new NullPointerException();
		}
		long stamp = lock.readLock();
		try {
			return m.get(k);
		} finally {
			lock.unlockRead(stamp);
		}
	}

	public void put(K k, V v) {
		if (k == null || v == null) {
			throw new NullPointerException();
		}
		long stamp = lock.writeLock();
		try {
			m.put(k, v);
		} finally {
			lock.unlockWrite(stamp);
		}
	}

}
