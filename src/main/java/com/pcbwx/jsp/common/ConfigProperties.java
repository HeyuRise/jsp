package com.pcbwx.jsp.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pcbwx.jsp.SystemStart;
import com.pcbwx.jsp.enums.ConfigEnum;
import com.pcbwx.jsp.exception.BusinessException;
import com.pcbwx.jsp.exception.ExceptionType;

/**
 * 初始化静态变量
 * @author 孙贺宇
 *
 */
public class ConfigProperties {
	
	private static Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
	
	private static Integer debug;
	private static String mainUrl;

	public static final Properties props = new Properties();
	
	public static void load() throws IOException{
		String fileName = "config.properties";
		String configFile = System.getenv("CONFIG_SPACE") + "/" + SystemStart.MYSYSTEMCODE + "/" + fileName;
		InputStream in = null;
		try {
			in = new FileInputStream(new File(configFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (in != null) {
			try {
				props.load(in);
			} catch (IOException e) {
				throw new BusinessException(ExceptionType.EXCEPTION_400, "未找到config.properties文件");
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
		}
		debug = ConfigProperties.getPropertyInt(ConfigEnum.DEBUG_WITHOUT_LOGIN);
		mainUrl = ConfigProperties.getProperty(ConfigEnum.SERVICE_MAIN_URL);
	}
	/**
	 * 获取静态变量参数
	 * @param constant
	 * @return
	 */
	public static Integer getDebug() {
		return debug;
	}
	public static String getMainUrl() {
		return mainUrl;
	}
	/**
	 * 获取静态变量参数
	 * @param constant
	 * @return
	 */
	public static String getProperty(ConfigEnum config){
		return props.getProperty(config.getCode());
	}
	/**
	 * 获取静态变量参数
	 * @param constant
	 * @return
	 */
	public static String getProperty(ConfigEnum config, String defValue){
		String value = props.getProperty(config.getCode());
		if (value == null) {
			return defValue;
		}
		return value;
	}
	public static Integer getPropertyInt(ConfigEnum constant){
		String value = props.getProperty(constant.getCode());
		if (value == null) {
			logger.error("找不到该配置:" + constant.getCode());
			return null;
		}
		return Integer.valueOf(value);
	}
	public static Integer getPropertyInt(ConfigEnum constant, Integer defValue){
		String value = props.getProperty(constant.getCode());
		if (value == null) {
			return defValue;
		}
		return Integer.valueOf(value);
	}
}
