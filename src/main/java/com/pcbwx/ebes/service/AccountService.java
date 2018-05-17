package com.pcbwx.ebes.service;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import com.pcbwx.ebes.bean.User;


/**
 * 用户会话模块业务接口
 * @author 王海龙
 */

public interface AccountService {
	
	/**
	 * 获取图片流
	 * @param height
	 * @param width
	 * @param format
	 * @param type
	 * @param text
	 * @return
	 */
	ByteArrayOutputStream trans(Integer height, Integer width, String format, Integer type, String text);

	/**
	 * 获取按钮是否显示，是否能点击
	 * @param account
	 * @param buttonId
	 * @return
	 */
	Boolean getButtonAppear(String account, Integer buttonId);
	
	
	User getWxtbUser();
	
	/**
	 * 获取用户详细·信息
	 * 
	 * @param wxtbUser
	 * @return
	 */
	Map<String, Object> getUserDetail(User wxtbUser);
}
