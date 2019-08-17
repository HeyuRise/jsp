package heyu;

import java.util.Date;

import com.heyu.jsp.util.DateTimeUtil;

/**
 * @author heyu
 * @date 2019/8/17
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        String date = DateTimeUtil.date2dateStr(new Date());
        String date2 = DateTimeUtil.date2dateStr(new Date());
        String date3 = DateTimeUtil.date2dateTimeStr(new Date());
        String date4 = DateTimeUtil.date2dateTimeStr(new Date());

    }

    private void aaa(){

    }
}
