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
 * 
 * @author 孙贺宇
 * @version 1.0
 * 
 */
public class ConfigProperties {

	private static Logger logger = LoggerFactory.getLogger(ConfigProperties.class);

	private static final String FILENAME = "config.properties";

	public static final Properties props = new Properties();

	static {
		String configFile = System.getenv("CONFIG_SPACE") + "/" + SystemStart.MYSYSTEMCODE + "/" + FILENAME;
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
				throw new BusinessException(ExceptionType.EXCEPTION_400, "未找到" + FILENAME + "文件");
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}

	public static String getProperty(ConfigEnum config) {
		String value = props.getProperty(config.getCode());
		if (value == null) {
			logger.error("找不到该配置:" + config.getCode());
			return value;
		}
		return value;
	}

	public static String getProperty(ConfigEnum config, String defValue) {
		String value = props.getProperty(config.getCode());
		if (value == null) {
			logger.error("找不到该配置:" + config.getCode());
			return defValue;
		}
		return value;
	}

	public static Integer getPropertyInt(ConfigEnum constant) {
		String value = props.getProperty(constant.getCode());
		if (value == null) {
			logger.error("找不到该配置:" + constant.getCode());
			return null;
		}
		return Integer.valueOf(value);
	}

	public static Integer getPropertyInt(ConfigEnum constant, Integer defValue) {
		String value = props.getProperty(constant.getCode());
		if (value == null) {
			logger.error("找不到该配置:" + constant.getCode());
			return defValue;
		}
		return Integer.valueOf(value);
	}
}
