package com.pcbwx.jsp.service;

import java.util.List;

import com.pcbwx.jsp.enums.DictionaryEnum;
import com.pcbwx.jsp.model.Dictionary;

public interface RedisService {
	
	void reloadDictionary(List<Dictionary> dictionarys);
	
	Dictionary getDictionary(DictionaryEnum type, Integer innerId);
	Dictionary getDictionary(DictionaryEnum type, String innerCode);
	
	List<Dictionary> getDictionarys(DictionaryEnum type);

}
