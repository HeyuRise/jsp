package heyu;

import com.pcbwx.jsp.common.ConfigProperties;
import com.pcbwx.jsp.enums.ConfigEnum;

public class HeyuTest {
	
	public static void main(String[] args) {
		System.out.println(ConfigProperties.getProperty(ConfigEnum.DEBUG_WITHOUT_LOGIN));
	}

}
