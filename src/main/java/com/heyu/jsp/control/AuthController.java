package com.heyu.jsp.control;

import com.heyu.jsp.bean.MyResponse;
import com.heyu.jsp.bean.User;
import com.heyu.jsp.enums.DictionaryEnum;
import com.heyu.jsp.enums.ErrorCodeEnum;
import com.heyu.jsp.model.Dictionary;
import com.heyu.jsp.service.AccountService;
import com.heyu.jsp.service.RedisService;
import com.heyu.jsp.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author heyu
 * @date 2018-09-01
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "权限api")
public class AuthController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private RedisTemplate<String, String> stringTemplate;
	@Autowired
	private RedisService redisService;

	@ApiOperation("获取用户详情")
	@GetMapping("/userDetail")
	public MyResponse<Object> getUserAuths(HttpServletRequest request) {
		MyResponse<Object> response = new MyResponse<>();
		User wxtbUser = accountService.getWxtbUser();
		if (wxtbUser == null) {
			response.setCodeAndMsg(ErrorCodeEnum.SYSTEM_ERROR);
			return response;
		}
		Map<String, Object> userDetail = accountService.getUserDetail(wxtbUser);
		response.setSuccess(userDetail);
		return response;
	}

	@ApiOperation("查看按钮是否显示")
	@GetMapping("/button")
	public MyResponse<Object> buttonAppear(HttpServletRequest request,
			@RequestParam("buttonId") Integer buttonId) {
		MyResponse<Object> response = new MyResponse<>();
		User wxtbUser = accountService.getWxtbUser();
		boolean idAppear = accountService.getButtonAppear(
				wxtbUser.getAccount(), buttonId);
		if (idAppear) {
			response.setCodeAndMsg(ErrorCodeEnum.SUCCESS);
			return response;
		} 
		response.setCodeAndMsg(ErrorCodeEnum.SYSTEM_ERROR);
		return response;
	}

	@GetMapping("/test")
	public Object test(HttpServletRequest request){
		String domain = request.getServerName();
		String servlet = request.getServletPath();
		log.info(domain);
		log.info(servlet);
		return null;
	}

	@GetMapping("/testRedis")
	public Object testRedis(HttpServletRequest request){
		String a = stringTemplate.opsForValue().get("test:test:1");
		log.info(a);
		Dictionary dictionary = redisService.getDictionary(DictionaryEnum.BUTTON, 1);
		log.info(JsonUtil.obj2json(dictionary));
		return a;
	}
	
}
