package heyu;

import org.apache.logging.log4j.Level;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataRedisTest
public class HeyuTest {

	public static void main(String[] args){
		System.out.println(Boolean.valueOf("true"));
        Level.INFO.name();
        System.out.println( Level.INFO);
	}

}
