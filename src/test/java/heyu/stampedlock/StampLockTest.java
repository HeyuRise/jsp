package heyu.stampedlock;

/**
 * @author heyu
 * @date 2019/5/5
 */
public class StampLockTest {

    public static void main(String[] args) {
        Cache<Integer, String> cache = new Cache<>();
        cache.put(1, "1");
        System.out.println(cache.getOpt(1));
        System.out.println(cache.get(1));
    }
}
