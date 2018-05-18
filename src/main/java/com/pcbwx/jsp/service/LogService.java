package com.pcbwx.jsp.service;

import java.util.List;

import com.pcbwx.jsp.model.Log;

/**
 * 日志模块业务接口
 * 
 * @author 王海龙
 *
 */
public interface LogService {

	int addAction(String code, String action, String user, String value);
	int addAction(String code, String action, String user, String value, String param1);
	int addAction(String code, String action, String user, String value, String param1, String param2);
	int addAction(String code, String action, String user, String value, String param1, String param2, String param3);
	int addAction(String code, String action, String user, String value, List<String> params);
	
	/**
	 * 添加日志
	 * 
	 * @param log
	 *            日志wrapper对象
	 * @return 添加成功日志的ID值
	 */
	int addLog(Log log);

}
