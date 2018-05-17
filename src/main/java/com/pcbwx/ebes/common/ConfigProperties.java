package com.pcbwx.ebes.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pcbwx.ebes.enums.ConfigEnum;
import com.pcbwx.ebes.exception.BusinessException;
import com.pcbwx.ebes.exception.ExceptionType;
import com.pcbwx.ebes.util.LogUtil;

/**
 * 初始化静态变量
 * @author 孙贺宇
 *
 */
public class ConfigProperties {
	
	private static Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
	
	private final static String MYSYSTEMCODE = "jsp";
	
	private static Integer debug;
	private static String mainUrl;
	private static String postPath;
	private static String apiCode;
	private static String checkCode;
	private static String addressUrl;
	private static String addressClock;
	private static String sendProtocol;
	private static String receiveProtocol;
	private static String sendHost;
	private static String receiveHost;
	private static String mailAccount;
	private static String mailPassword;
	private static String mailTarget;
	private static String innerMail;
	private static String mailnoFilePath;
	private static String orderIdHead;
	
	public static String getMySystemCode() {
		return MYSYSTEMCODE;
	}

	public static final Properties props = new Properties();
	
	public static void load() throws IOException{
		String fileName = "config.properties";
		String configFile = System.getenv("CONFIG_SPACE") + "/" + MYSYSTEMCODE + "/" + fileName;
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
		postPath = ConfigProperties.getProperty(ConfigEnum.POST_PATH);
		apiCode = ConfigProperties.getProperty(ConfigEnum.API_CODE);
		checkCode = ConfigProperties.getProperty(ConfigEnum.CHECK_WORD);
		addressUrl = ConfigProperties.getProperty(ConfigEnum.ADDRESS_URL);
		addressClock = ConfigProperties.getProperty(ConfigEnum.ADDRESS_CLOCK);
		sendProtocol = ConfigProperties.getProperty(ConfigEnum.SEND_MAIL_TRANSPORT_PROTOCOL);
		receiveProtocol = ConfigProperties.getProperty(ConfigEnum.RECEIVE_MAIL_TRANSPORT_PROTOCOL);
		sendHost = ConfigProperties.getProperty(ConfigEnum.SEND_MAIL_SMTP_HOST);
		receiveHost = ConfigProperties.getProperty(ConfigEnum.RECEIVE_MAIL_SMTP_HOST);
		mailAccount = ConfigProperties.getProperty(ConfigEnum.MAIL_ACCOUNT);
		mailPassword = ConfigProperties.getProperty(ConfigEnum.MAIL_PASSWORD);
		mailTarget = ConfigProperties.getProperty(ConfigEnum.MAIL_TARGET_ACCOUNT);
		innerMail = ConfigProperties.getProperty(ConfigEnum.INNER_MAIL);
		mailnoFilePath = ConfigProperties.getProperty(ConfigEnum.MAILNO_FILEPATH);
		orderIdHead = ConfigProperties.getProperty(ConfigEnum.ORDER_ID_HEAD);
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
	public static String getPostPath() {
		return postPath;
	}
	public static String getApiCode() {
		return apiCode;
	}
	public static String getCheckCode() {
		return checkCode;
	}
	public static String getAddressUrl(){
		return addressUrl;
	}
	public static String getAddressClock(){
		return addressClock;
	}
	public static String getSendProtocol(){
		return sendProtocol;
	}
	public static String getReceiveProtocol(){
		return receiveProtocol;
	}
	public static String getSendHost(){
		return sendHost;
	}
	public static String getReceiveHost(){
		return receiveHost;
	}
	public static String getMailAccount(){
		return mailAccount;
	}
	public static String getMailPaddword(){
		return mailPassword;
	}
	public static String getMailTargetMail(){
		return mailTarget;
	}
	public static String getInnerMail(){
		return innerMail;
	}
	public static String getMailnoFilePath(){
		return mailnoFilePath;
	}
	public static String getOrderIdHead(){
		return orderIdHead;
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
			LogUtil.logger(LogUtil.printLine(), logger, "找不到该配置:" + constant.getCode(), Level.ERROR);
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
