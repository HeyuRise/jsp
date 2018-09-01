package com.pcbwx.jsp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.pcbwx.jsp.SystemStart;
import com.pcbwx.jsp.enums.DictionaryEnum;
import com.pcbwx.jsp.enums.RedisKeyEnum;
import com.pcbwx.jsp.model.Dictionary;
import com.pcbwx.jsp.service.RedisService;
import com.pcbwx.jsp.util.DataUtil;
import com.pcbwx.jsp.util.JsonUtil;

/**
 * redis 缓存服务
 *
 * @author heyu
 * @date 2018-09-01
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {
	
	private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

	@Autowired
	private RedisTemplate<String, Dictionary> templateDic;

	@Override
	public void reloadDictionary(List<Dictionary> dictionarys) {
		ValueOperations<String, Dictionary> operation = templateDic.opsForValue();
		for (Dictionary record : dictionarys) {
			String key = SystemStart.MYSYSTEMCODE + ":" + RedisKeyEnum.DICTIONARY.getCode() + ":" + record.getType();
			if (record.getInnerId() != null) {
				key = key + ":" + record.getInnerId();
				operation.set(key, record);
				continue;
			}
			if (record.getInnerCode() != null) {
				key = key + ":" + record.getInnerCode();
				operation.set(key, record);
				continue;
			}
		}
		// #-------------------------------------------------------------

		Map<String, List<Dictionary>> dicCache = new HashMap<>(50);
		try {
			dicCache = DataUtil.list2mapList(dictionarys, Dictionary.class, "type");
			logger.info("字典类别缓存条数:" + dicCache.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListOperations<String, Dictionary> listOperation = templateDic.opsForList();
		for (Map.Entry<String, List<Dictionary>> entry : dicCache.entrySet()) {
			String key = SystemStart.MYSYSTEMCODE + ":" + RedisKeyEnum.DICTIONARY.getCode() + ":" + entry.getKey();
            templateDic.delete(key);
			List<Dictionary> dic = entry.getValue();
			for (Dictionary dictionary : dic) {
				listOperation.leftPush(key, dictionary);
			}
		}
		
	}

	@Override
	public Dictionary getDictionary(DictionaryEnum type, Integer innerId) {
		String key = SystemStart.MYSYSTEMCODE + ":" + RedisKeyEnum.DICTIONARY.getCode() + ":" + type.getCode() + ":"
				+ innerId;
		return templateDic.opsForValue().get(key);
	}

	@Override
	public Dictionary getDictionary(DictionaryEnum type, String innerCode) {
		String key = SystemStart.MYSYSTEMCODE + ":" + RedisKeyEnum.DICTIONARY.getCode() + ":" + type.getCode() + ":"
				+ innerCode;
		return templateDic.opsForValue().get(key);
	}

	@Override
	public List<Dictionary> getDictionarys(DictionaryEnum type) {
		ListOperations<String, Dictionary> operation = templateDic.opsForList();
		String key = SystemStart.MYSYSTEMCODE + ":" + RedisKeyEnum.DICTIONARY.getCode() + ":" + type.getCode();
		List<Dictionary> list = operation.range(key, 0, -1);
		logger.info(JsonUtil.obj2json(list));
		List<Dictionary> dicList = new ArrayList<>();
		for (Dictionary object : list) {
			dicList.add(object);
		}
		return dicList;
	}

}
