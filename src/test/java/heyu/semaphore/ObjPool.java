package heyu.semaphore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @author heyu
 * @date 2019/5/5
 */
public class ObjPool<T, R> {

    private final Semaphore semaphore;

    private BlockingQueue<T> poll;

    public ObjPool(int size, T r) {
        semaphore = new Semaphore(size);
        poll = new LinkedBlockingQueue<>();
        for (int i = 0; i< size; i++){
            poll.offer(r);
        }
    }

    R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        semaphore.acquire();
        try {
            t = poll.poll();
            return func.apply(t);
        } finally {
            poll.offer(t);
            semaphore.release();
        }
    }
}
