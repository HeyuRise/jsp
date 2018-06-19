package heyu;

import com.pcbwx.jsp.util.RedisClient;

public class HeyuTest {
	
	public static void main(String[] args) {
		RedisClient redisClient = new RedisClient();
		
		redisClient.show();
		System.out.println("========================================");
	}

}
