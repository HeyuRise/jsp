package heyu;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@DataRedisTest
public class HeyuTest {

	public static void main(String[] args){
		System.out.println(Boolean.valueOf("true"));
	}

}
