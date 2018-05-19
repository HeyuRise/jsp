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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.jsp.dao.WxtbUserMapper;
import com.pcbwx.jsp.model.WxtbUser;
import com.pcbwx.jsp.util.DateTimeUtil;

@Controller
public class WelcomeController {
	
	private Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@Autowired
	public WxtbUserMapper wxtbUserMapper;

	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
	
	// 登录页
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> test(@RequestParam("date") Date date){
		logger.info(DateTimeUtil.date2dateStr(date));
		Map<String, Object> response = new HashMap<>();
		List<WxtbUser> wxtbUsers = wxtbUserMapper.load();
		for (WxtbUser wxtbUser : wxtbUsers) {
			wxtbUser.setUsername(null);
		}
		response.put("data", wxtbUsers);
		return response;
	}
	
}
