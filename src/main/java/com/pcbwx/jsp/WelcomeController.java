/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pcbwx.jsp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.jsp.bean.MyResponse;
import com.pcbwx.jsp.common.ConfigProperties;
import com.pcbwx.jsp.dao.WxtbUserMapper;
import com.pcbwx.jsp.enums.ConfigEnum;
import com.pcbwx.jsp.enums.DictionaryEnum;
import com.pcbwx.jsp.model.WxtbUser;
import com.pcbwx.jsp.service.RedisService;

/**
 * demos
 *
 * @author heyu
 * @date 2018-09-01
 */
@Controller
public class WelcomeController {
	
	private Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@Autowired
	public WxtbUserMapper wxtbUserMapper;
	
	@Autowired
	private RedisService redisService;

	@GetMapping("/redis-list")
	@ResponseBody
	public Object redislist() {
		return redisService.getDictionarys(DictionaryEnum.PAY_METHOD);
	}

	@GetMapping("/redis")
	@ResponseBody
	public Object welcome2() {
		return redisService.getDictionary(DictionaryEnum.PAY_METHOD,1);
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/index")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/test")
	@ResponseBody
	public MyResponse<Object> test(){
	    MyResponse<Object> response = new MyResponse<>();
		List<WxtbUser> wxtbUsers = wxtbUserMapper.load();
		for (WxtbUser wxtbUser : wxtbUsers) {
			wxtbUser.setUsername(null);
		}
		logger.info(ConfigProperties.getProperty(ConfigEnum.SERVICE_MAIN_URL));
		response.setSuccess(wxtbUsers);
		return response;
	}

}
