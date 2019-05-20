package heyu.guarded;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author heyu
 * @date 2019/5/20
 */
public class GuardedTest {

	public static void main(String[] args) throws InterruptedException {
        long id = 1L;
        new Thread(() -> {
            Message message = handleWebReq(id);
            System.out.println(message);
        }).start();
        TimeUnit.SECONDS.sleep(2);
		new Thread(() -> onMessage(new Message(id, "xxxxxxxxxxxxxxx"))).start();
	}

    /**
     * 处理浏览器发来的请求
     * @param id
     * @return
     */
	private static Message handleWebReq(long id) {

		// 创建一消息
		// 创建 GuardedObject 实例
		GuardedObject<Message> go = GuardedObject.create(id);
		// 发送消息
		// send(msg1);
		// 等待 MQ 消息
		return go.get(Objects::nonNull);
	}

    /**
     * 消息返回
     * @param msg
     */
	private static void onMessage(Message msg) {
		// 唤醒等待的线程
		GuardedObject.fireEvent(msg.getId(), msg);
	}

}
