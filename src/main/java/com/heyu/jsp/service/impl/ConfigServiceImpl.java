package com.heyu.jsp.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;

import com.heyu.jsp.model.Config;
import com.heyu.jsp.model.RecordUtils;
import com.heyu.jsp.model.TaskClock;
import com.heyu.jsp.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heyu.jsp.dao.ConfigMapper;
import com.heyu.jsp.dao.RecordUtilsMapper;
import com.heyu.jsp.dao.TaskClockMapper;
import com.heyu.jsp.enums.ConfigEnum;
import com.heyu.jsp.enums.TaskClockEnum;
import com.heyu.jsp.util.DateCalcUtil;
import com.heyu.jsp.util.DateTimeUtil;

/**
 * 配置接口实现类
 * 
 * @author 孙贺宇
 *
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {

	private static Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

	@Autowired
	private ConfigMapper configMapper;
	@Autowired
	private RecordUtilsMapper recordUtilsMapper;
	@Autowired
	private TaskClockMapper taskClockMapper;

	private Hashtable<String, Config> configCache = new Hashtable<String, Config>();

	// @PostConstruct
	public void initLoad() {
		reloadConfig();
	}

	@Override
	public void reloadConfig() {
		logger.info("开始载入配置...");
		configCache.clear();
		List<Config> configs = configMapper.load();
		if (configs != null && !configs.isEmpty()) {
			for (Config record : configs) {
				String key = record.getCfgName();
				configCache.put(key, record);
			}
			logger.info("配置缓存条数:" + configCache.size());
		}
	}
	
	@Override
	public Integer readIntConfig(ConfigEnum option) {
		Config config = configCache.get(option.getCode());
		if (config != null) {
			return config.getValueInt();
		}	
		return null;
	}	

	@Override
	public String readStrConfig(ConfigEnum option) {
		Config config = configCache.get(option.getCode());
		if (config != null) {
			return config.getValueStr();
		}	
		return null;
	}	

	@Override
	public Date readDateConfig(ConfigEnum option) {
		Config config = configCache.get(option.getCode());
		if (config != null) {
			return config.getValueTime();
		}	
		return null;
	}	

	@Override
	public RecordUtils getUtilRecord(ConfigEnum config) {
		return recordUtilsMapper.selectByName(config.getCode());
	}

	@Override
	public Integer getUtilInt(ConfigEnum config) {
		RecordUtils record = this.getUtilRecord(config);
		if (record != null && record.getValueInt() != null) {
			return record.getValueInt();
		}
		return null;
	}
	@Override
	public String getUtilStr(ConfigEnum config) {
		RecordUtils record = this.getUtilRecord(config);
		if (record != null && record.getValueStr() != null) {
			return record.getValueStr();
		}
		return null;
	}
	@Override
	public Date getUtilDate(ConfigEnum config) {
		RecordUtils record = this.getUtilRecord(config);
		if (record != null && record.getValueTime() != null) {
			return record.getValueTime();
		}
		return null;
	}
	
	@Override
	public boolean setUtilRecord(ConfigEnum config, String valueStr, Integer valueInt, Date valueTime, String param) {
		RecordUtils record = new RecordUtils();
		record.setRecordName(config.getCode());
		record.setDescription(config.getDescr());
		record.setValueInt(valueInt);
		record.setValueStr(valueStr);
		record.setValueTime(valueTime);
		record.setParam(param);
		record.setDescription(config.getDescr());
		
		int updated = recordUtilsMapper.updateByName(record);
		if (updated > 0) {
			return true;
		}
		int added = recordUtilsMapper.insertSelective(record);
		if (added > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean setUtilRecord(ConfigEnum config, String value, String param) {
		RecordUtils record = new RecordUtils();
		record.setRecordName(config.getCode());
		record.setDescription(config.getDescr());
		record.setValueStr(value);
		record.setParam(param);
		record.setDescription(config.getDescr());
		
		int updated = recordUtilsMapper.updateByName(record);
		if (updated > 0) {
			return true;
		}
		int added = recordUtilsMapper.insertSelective(record);
		if (added > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean setUtilRecord(ConfigEnum config, Integer value, String param) {
		RecordUtils record = new RecordUtils();
		record.setRecordName(config.getCode());
		record.setDescription(config.getDescr());
		record.setValueInt(value);
		record.setParam(param);
		record.setDescription(config.getDescr());
		
		int updated = recordUtilsMapper.updateByName(record);
		if (updated > 0) {
			return true;
		}
		int added = recordUtilsMapper.insertSelective(record);
		if (added > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean setUtilRecord(ConfigEnum config, Date value, String param) {
		RecordUtils record = new RecordUtils();
		record.setRecordName(config.getCode());
		record.setDescription(config.getDescr());
		record.setValueTime(value);
		record.setParam(param);
		record.setDescription(config.getDescr());
		
		int updated = recordUtilsMapper.updateByName(record);
		if (updated > 0) {
			return true;
		}
		int added = recordUtilsMapper.insertSelective(record);
		if (added > 0) {
			return true;
		}
		return false;
	}

	@Override
	public RecordUtils getUtilRecord(String config) {
		return recordUtilsMapper.selectByName(config);
	}

	@Override
	public Integer getUtilInt(String config) {
		RecordUtils record = this.getUtilRecord(config);
		if (record != null && record.getValueInt() != null) {
			return record.getValueInt();
		}
		return null;
	}
	@Override
	public String getUtilStr(String config) {
		RecordUtils record = this.getUtilRecord(config);
		if (record != null && record.getValueStr() != null) {
			return record.getValueStr();
		}
		return null;
	}
	@Override
	public Date getUtilDate(String config) {
		RecordUtils record = this.getUtilRecord(config);
		if (record != null && record.getValueTime() != null) {
			return record.getValueTime();
		}
		return null;
	}
	
	@Override
	public boolean setUtilRecord(String config, String desc, String valueStr, Integer valueInt, Date valueTime, String param) {
		RecordUtils record = new RecordUtils();
		record.setRecordName(config);
		record.setDescription(desc);
		record.setValueInt(valueInt);
		record.setValueStr(valueStr);
		record.setValueTime(valueTime);
		record.setParam(param);
		
		int updated = recordUtilsMapper.updateByName(record);
		if (updated > 0) {
			return true;
		}
		int added = recordUtilsMapper.insertSelective(record);
		if (added > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean setUtilRecord(String config, String desc, String value, String param) {
		RecordUtils record = new RecordUtils();
		record.setRecordName(config);
		record.setDescription(desc);
		record.setValueStr(value);
		record.setParam(param);
		
		int updated = recordUtilsMapper.updateByName(record);
		if (updated > 0) {
			return true;
		}
		int added = recordUtilsMapper.insertSelective(record);
		if (added > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean setUtilRecord(String config, String desc, Integer value, String param) {
		RecordUtils record = new RecordUtils();
		record.setRecordName(config);
		record.setDescription(desc);
		record.setValueInt(value);
		record.setParam(param);
		
		int updated = recordUtilsMapper.updateByName(record);
		if (updated > 0) {
			return true;
		}
		int added = recordUtilsMapper.insertSelective(record);
		if (added > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean setUtilRecord(String config, String desc, Date value, String param) {
		RecordUtils record = new RecordUtils();
		record.setRecordName(config);
		record.setDescription(desc);
		record.setValueTime(value);
		record.setParam(param);
		
		int updated = recordUtilsMapper.updateByName(record);
		if (updated > 0) {
			return true;
		}
		int added = recordUtilsMapper.insertSelective(record);
		if (added > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isTaskTimeOverClock(Date now, ConfigEnum lastActionTime, TaskClockEnum task) {
		RecordUtils exportTime = this.getUtilRecord(lastActionTime);
		
		String taskName = null;
		if (task != null) {
			taskName = task.getCode();
		}
		List<TaskClock> times = taskClockMapper.selectByTask(taskName);
		if (times == null || times.isEmpty()) {
			// 如果是单项配置，找不到，采用默认配置
			if (task != null) {
				times = taskClockMapper.selectByTask(TaskClockEnum.DEFAULT.getCode());
			}			
		}
		if (times == null || times.isEmpty()) {
			logger.error("导出时间配置", "没有导出数据任务的时间点配置！！！！！");
			return false;
		}
		for (TaskClock clock : times) {
			Date date = DateTimeUtil.truncateDateTime(now);
			Date clockDate = DateTimeUtil.truncateDateTime(clock.getClock());
			int extSeconds = (int) ((clock.getClock().getTime() - clockDate.getTime()) / 1000);
			date = DateCalcUtil.addTime(date, Calendar.SECOND, extSeconds);
			if (now.compareTo(date) >= 0) {
				if (exportTime == null || exportTime.getValueTime() == null || exportTime.getValueTime().before(date)) {
					return true;
				}
			}
		}
		return false;
	}
}
