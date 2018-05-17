package com.pcbwx.ebes.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.ebes.bean.User;
import com.pcbwx.ebes.enums.ErrorCodeEnum;
import com.pcbwx.ebes.service.AccountService;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
	
	@Autowired
	private AccountService accountService;
//	@Autowired
//	private LogService logService;

	@RequestMapping(value = { "/userDetail" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserAuths(HttpServletRequest request) {
		User wxtbUser = accountService.getWxtbUser();
		Map<String, Object> response = new HashMap<String, Object>();
		if (wxtbUser == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			return response;
		}
		response = accountService.getUserDetail(wxtbUser);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		return response;
	}

//	@RequestMapping(value = { "/password" }, method = RequestMethod.PATCH)
//	@ResponseBody
//	public Map<String, Object> verfityPassword(HttpServletRequest request,
//			@RequestParam("account") String account,
//			@RequestParam("oldPassword") String oldpassword,
//			@RequestParam("newPassword") String password) {
//		Map<String, Object> response = new HashMap<String, Object>();
//		Integer result = userService.vertifyPassword(account, oldpassword,
//				password);
//		if (result == 1) {
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			response.put("msg", "系统错误");
//			return response;
//		} else if (result == 2) {
//			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
//			response.put("msg", "旧密码错误");
//			return response;
//		}
//		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
//		return response;
//	}

	@RequestMapping(value = { "/button" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> buttonAppear(HttpServletRequest request,
			@RequestParam("buttonId") Integer buttonId) {
		Map<String, Object> response = new HashMap<String, Object>();
		User wxtbUser = accountService.getWxtbUser();
		boolean idAppear = accountService.getButtonAppear(
				wxtbUser.getAccount(), buttonId);
		if (idAppear) {
			response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		} else {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
		}
		return response;
	}
}
