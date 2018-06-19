package com.pcbwx.jsp.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.jsp.dao.ActionLogMapper;
import com.pcbwx.jsp.dao.LogMapper;
import com.pcbwx.jsp.model.ActionLog;
import com.pcbwx.jsp.model.Log;
import com.pcbwx.jsp.service.LogService;

/**
 * 日志接口实现类
 * 
 * @author 孙贺宇
 *
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    private static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

	@Autowired
	private LogMapper logMapper;
	@Autowired
	private ActionLogMapper actionLogMapper;

	@Override
	public int addAction(String code, String action, String user, String value) {
		try {
			ActionLog record = new ActionLog();
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public int addAction(String code, String action, String user, String value,
			String param1) {
		try {
			ActionLog record = new ActionLog();
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			record.setParam1(param1);
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public int addAction(String code, String action, String user, String value,
			String param1, String param2) {
		try {
			ActionLog record = new ActionLog();
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			record.setParam1(param1);
			record.setParam2(param2);
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public int addAction(String code, String action, String user, String value,
			String param1, String param2, String param3) {
		try {
			ActionLog record = new ActionLog();
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			record.setParam1(param1);
			record.setParam2(param2);
			record.setParam3(param3);
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public int addAction(String code, String action, String user, String value,
			List<String> params) {
		try {
			ActionLog record = new ActionLog();
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			for (int i = 0; i < params.size(); i++) {
				if (i == 0) {
					record.setParam1(params.get(i));
				}
				if (i == 1) {
					record.setParam2(params.get(i));
				}
				if (i == 2) {
					record.setParam3(params.get(i));
				}
				if (i == 3) {
					record.setParam4(params.get(i));
				} else {
					break;
				}
			}
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public int addLog(Log log) {
		try {
			return logMapper.insertSelective(log);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

}
