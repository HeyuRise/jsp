package heyu.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author heyu
 * @date 2019/5/5
 */
public class Cache<K, V> {

	final Map<K, V> m = new HashMap<>();

	final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	final Lock readLock = readWriteLock.readLock();

	final Lock writeLock = readWriteLock.writeLock();

	V get(K k) {
		if (k == null) {
			throw new NullPointerException();
		}
		readLock.lock();
		try {
			return m.get(k);
		} finally {
			readLock.unlock();
		}
	}

	void put(K k, V v){
	    if (k == null || v == null){
            throw new NullPointerException();
        }
        writeLock.lock();
	    try {
	        m.put(k, v);
        } finally {
	        writeLock.unlock();
        }
    }

}
