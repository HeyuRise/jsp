package com.heyu.jsp.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heyu.jsp.bean.MyResponse;
import com.heyu.jsp.bean.User;
import com.heyu.jsp.enums.ErrorCodeEnum;
import com.heyu.jsp.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @author heyu
 * @date 2018-09-01
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "权限api")
public class AuthController {
	
	@Autowired
	private AccountService accountService;

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
	
}
