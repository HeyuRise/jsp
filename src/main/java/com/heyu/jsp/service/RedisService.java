package com.heyu.jsp.service;

import java.util.List;

import com.heyu.jsp.model.Dictionary;
import com.heyu.jsp.enums.DictionaryEnum;

public interface RedisService {
	
	void reloadDictionary(List<Dictionary> dictionarys);
	
	Dictionary getDictionary(DictionaryEnum type, Integer innerId);
	Dictionary getDictionary(DictionaryEnum type, String innerCode);
	
	List<Dictionary> getDictionarys(DictionaryEnum type);

}
