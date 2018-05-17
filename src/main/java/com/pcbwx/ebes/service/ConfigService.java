package com.pcbwx.ebes.service;

import java.util.Date;

import com.pcbwx.ebes.enums.ConfigEnum;
import com.pcbwx.ebes.enums.TaskClockEnum;
import com.pcbwx.ebes.model.RecordUtils;

/**
 * 日志模块业务接口
 * 
 * @author 王海龙
 *
 */
public interface ConfigService {
	void reloadConfig();
	
	Integer readIntConfig(ConfigEnum option);
	String readStrConfig(ConfigEnum option);
	Date readDateConfig(ConfigEnum option);
	
	RecordUtils getUtilRecord(ConfigEnum config);
	Integer getUtilInt(ConfigEnum config);
	String getUtilStr(ConfigEnum config);
	Date getUtilDate(ConfigEnum config);
	
	boolean setUtilRecord(ConfigEnum config, String valueStr, Integer valueInt, Date valueTime, String param);
	boolean setUtilRecord(ConfigEnum config, String value, String param);
	boolean setUtilRecord(ConfigEnum config, Integer value, String param);
	boolean setUtilRecord(ConfigEnum config, Date value, String param);

	RecordUtils getUtilRecord(String config);
	Integer getUtilInt(String config);
	String getUtilStr(String config);
	Date getUtilDate(String config);
	
	boolean setUtilRecord(String config, String desc, String valueStr, Integer valueInt, Date valueTime, String param);
	boolean setUtilRecord(String config, String desc, String value, String param);
	boolean setUtilRecord(String config, String desc, Integer value, String param);
	boolean setUtilRecord(String config, String desc, Date value, String param);
	
	boolean isTaskTimeOverClock(Date now, ConfigEnum lastActionTime, TaskClockEnum task);
}
