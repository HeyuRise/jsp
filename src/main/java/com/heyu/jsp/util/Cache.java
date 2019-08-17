package com.heyu.jsp.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author heyu
 * @date 2019/8/17
 */
public class Cache<K, V> {
    /**
     * 存储数据对象
     */
	private final Map<K, V> m = new HashMap<>();
    /**
     * 读写锁
     */
	private final ReadWriteLock rwl = new ReentrantReadWriteLock();
    /**
     * 读锁
     */
	private final Lock r = rwl.readLock();
    /**
     * 写锁
     */
	private final Lock w = rwl.writeLock();

	/**
	 * 读缓存
	 * 
	 * @param key
	 *            key
	 * @return value
	 */
	public V get(K key) {
		r.lock();
		try {
			return m.get(key);
		} finally {
			r.unlock();
		}
	}

	/**
	 * 写缓存
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public void put(K key, V value) {
		w.lock();
		try {
			m.put(key, value);
		} finally {
			w.unlock();
		}
	}
}
