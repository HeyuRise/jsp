package com.pcbwx.jsp.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.pcbwx.jsp.bean.MainMenu;
import com.pcbwx.jsp.bean.MenuItem;
import com.pcbwx.jsp.bean.User;
import com.pcbwx.jsp.enums.DictionaryEnum;
import com.pcbwx.jsp.model.Dictionary;
import com.pcbwx.jsp.model.Menu;
import com.pcbwx.jsp.model.UserRole;
import com.pcbwx.jsp.service.AccountService;
import com.pcbwx.jsp.service.CacheService;
import com.pcbwx.jsp.service.SupportService;

/**
 * 用户会话模块业务接口实现类
 * 
 * @author 王海龙
 *
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private CacheService cacheService;
	@Autowired
	private SupportService supportService;
	
	@Override
	public Map<String, Object> getUserDetail(User wxtbUser) {
		Map<String, Object> response = new HashMap<String, Object>();
		String account = wxtbUser.getAccount();
		String username = wxtbUser.getUsername();
		response.put("account", account);
		response.put("username", username);
		List<String> roleNames = new ArrayList<String>();
		List<UserRole> userRoles = cacheService.getUserRole(wxtbUser.getAccount());
		if (userRoles == null || userRoles.isEmpty()) {
			return response;
		}
		// 添加角色集合
		for (UserRole userRole : userRoles) {
			roleNames.add(userRole.getRoleName());
		}
		response.put("roles", roleNames);
		Set<Integer> authIds = supportService.getUserAuths(account);
		Map<Integer, Menu> menuCache = cacheService.getMenu();
		List<Menu> menus = new ArrayList<Menu>();
		List<Menu> secondMenus = new ArrayList<Menu>();
		for (Integer id : menuCache.keySet()) {
			Menu menu = menuCache.get(id);
			String authTypesStr = menu.getAuthType();
			if (authTypesStr == null) {
				continue;
			}
			// 按钮的权限集合
			String[] sp = authTypesStr.split(",");
			List<String> sps = new ArrayList<String>();
			for (String string : sp) {
				sps.add(string);
			}
			// 查看用户是否存在该权限
			for (Integer authId : authIds) {
				if (sps.contains(authId.toString())) {
					if (menu.getMenuLevel() == 1) {
						menus.add(menu);
					} else {
						secondMenus.add(menu);
					}
					break;
				}
			}
		}
		List<MainMenu> mainMenus = new ArrayList<MainMenu>();
		for (Menu menu : menus) {
			if (menu.getMenuLevel() == 1) {
				MainMenu mainMenu = new MainMenu();
				mainMenu.setTile(menu.getMenu());
				mainMenu.setParam(menu.getParam());
				mainMenu.setColor(menu.getColor());
				List<MenuItem> menuItems = new ArrayList<MenuItem>();
				Integer mainMenuId = menu.getMainMenuId();
				for (Menu menuSecond : secondMenus) {
					if (menuSecond.getMainMenuId() == mainMenuId) {
						MenuItem menuItem = new MenuItem();
						menuItem.setTile(menuSecond.getMenu());
						menuItem.setUrl(menuSecond.getMenuUrl());
						menuItems.add(menuItem);
						mainMenu.setMenuItems(menuItems);
					}
				}
				mainMenus.add(mainMenu);
			}
		}
		response.put("menu", mainMenus);
		return response;
	}

	@Override
	public ByteArrayOutputStream trans(Integer height, Integer width, String format, Integer type, String text) {
		height = height == null ? 1000 : height;
		width = width == null ? 1000 : width;
		if (format == null) {
			format = "png";
		}
		BarcodeFormat barcodeFormat = getBarcodeFormat(type);
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(text, barcodeFormat, width, height, hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);
		try {
			MatrixToImageWriter.writeToStream(bitMatrix, format, bout);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bout;

	}
	
	@Override
	public Boolean getButtonAppear(String account, Integer buttonId) {
		Dictionary dictionary = cacheService.getDictionary(DictionaryEnum.BUTTON, buttonId);
		if (dictionary == null) {
			return true;
		}
		
		// 获取按钮对应的权限
		Integer auth = dictionary.getParamInt1();
		// 获取用户所有权限
		Set<Integer> authIds = supportService.getUserAuths(account);
		// 查看用户权限是否包含这个按钮的权限
		if (authIds.contains(auth)) {
			return true;
		}
		return false;
	}
	
	@Override
	public User getWxtbUser() {
		User wxtbUser = null;
		try {
			wxtbUser = (User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wxtbUser;
	}
	
	private BarcodeFormat getBarcodeFormat(Integer type){
		BarcodeFormat barcodeFormat = BarcodeFormat.QR_CODE;
		if (type == null) {
			return barcodeFormat;
		}
		switch (type) {
		case 1:
			barcodeFormat = BarcodeFormat.CODE_128;
			break;
		case 2:
			barcodeFormat = BarcodeFormat.CODE_39;
			break;
		case 3:
			barcodeFormat = BarcodeFormat.CODE_93;
			break;
		default:
			barcodeFormat = BarcodeFormat.QR_CODE;
			break;
		}
		return barcodeFormat;
	}

}
