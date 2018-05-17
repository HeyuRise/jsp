package com.pcbwx.ebes.util;

import java.util.Objects;

import org.apache.logging.log4j.Level;
import org.slf4j.Logger;

public class LogUtil {
	
	private static Long LOGNUMBER = 1L;
	
	/**
	 * 给日志添加连续序号
	 * @param logger
	 * @param msg
	 * @param logLevel
	 */
	public static void logger(Integer where, Logger logger, String msg, Level logLevel) {
		LOGNUMBER = LOGNUMBER + 1;
		try {
			Integer logLevelInt = logLevel.intLevel();
			if (Objects.equals(logLevelInt, Level.ERROR.intLevel())) {
				logger.error("[" + where + "] -" + LOGNUMBER + "---" + msg);
			}else if (Objects.equals(logLevelInt, Level.WARN.intLevel())) {
				logger.warn("[" + where + "] -" + LOGNUMBER + "---" + msg);
			}else if (Objects.equals(logLevelInt, Level.DEBUG.intLevel())) {
				logger.debug("[" + where + "] -" + LOGNUMBER + "---" + msg);
			}else {
				logger.info("[" + where + "] -" + LOGNUMBER + "---" + msg);
			}
		} catch (Exception e) {
			logger.error("[" + where + "] -" + LOGNUMBER + "---" + e.getMessage());
		}
	}
	
	/**
	 * 获取调取该方法的行数
	 * @return
	 */
	public static Integer printLine(){
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        // 注意!这里是下标为2的,而不是为1的
        StackTraceElement tmp = trace[2];
        return tmp.getLineNumber();
    }
}
