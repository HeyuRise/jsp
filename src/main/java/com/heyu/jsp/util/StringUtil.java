package com.heyu.jsp.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 字符串工具类,用于处理字符串
 * @author 孙贺宇
 * @version 1.0
 */
public class StringUtil {

	 /**
     * 检查字符串是否为空
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        if(str!=null && str.length()>0){
        	return false;
        }
        return true;
    }
    /**
     * 判断字符串数组是否为空
     * @param strs
     * @return
     */
    public static boolean isEmpty(String[] strs) {
        if(strs!=null && strs.length>0){
        	return false;
        }
        return true;
    }
   
    /**
	 * 判断字符串中是否含有英文字符
	 * @param str
	 * @return
	 */
	public static boolean isContainsStr(String str){
		if(str!=null && str.length()>0){
			return Pattern.compile("(?i)[a-zA-Z]").matcher(str).find();
		}else{
			return false;
		}
	}
	
	/**
	 * 判断字符串是否是数字类型的
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 将首字母转换成小写
	 * @param str
	 * @return
	 */
	public static String toLowerFirst(String name) {
        char[] cs = name.toCharArray();
        if (cs[0] >= 'A' && cs[0] <= 'Z') {
        	cs[0] += 32;        	
        }
        return String.valueOf(cs);        
    }

	/**
	 * 将首字母转换成大写
	 * @param str
	 * @return
	 */
	public static String toUpperFirst(String name) {
        char[] cs = name.toCharArray();
        if (cs[0] >= 'a' && cs[0] <= 'z') {
        	cs[0] -= 32;        	
        }
        return String.valueOf(cs);        
    }

	/**
	 * 将字段名转换成get/set方法所需的字段名
	 * @param str
	 * @return
	 */
	public static String toMethodName(String name) {
        char[] cs = name.toCharArray();
        // 若第二位为大写，不转换第一位
        if (cs.length > 1 && cs[1] >= 'A' && cs[1] <= 'Z') {
        	return name;
        }
        // 将第一位转换成大写
        if (cs[0] >= 'a' && cs[0] <= 'z') {
        	cs[0] -= 32;        	
        }
        return String.valueOf(cs);        
    }
	
	/**
	 * 得到文件的扩展名
	 * @param str
	 * @return
	 */
	public static String getFileExtension(String str){
		
		if(str!=null && str.length()>0){
			return str.lastIndexOf(".") == -1 ? "" : str.substring(str.lastIndexOf(".") + 1);
		}else{
			return null;
		}
		
	}
	/**
	 * 判断字符串中是否含有中文字符
	 * @param str
	 * @return
	 */
	public static boolean isContainsChinese(String str){
		
		if(str!=null && str.length()>0){
			return Pattern.compile("(?i)[\u4e00-\u9fa5]").matcher(str).find();
		}else{
			return false;
		}
		
	}
	/**
	 * 判断字符串中是否含有数字
	 * @param str
	 * @return
	 */
	public static boolean isContainsNumeric(String str){
		
		if(str!=null && str.length()>0){
			return Pattern.compile("(?i)[0-9]+").matcher(str).find();
		}else{
			return false;
		}
		
	}
	
	public static Map<String,String> getChars(){
		
		Map<String,String> maps = new HashMap<String,String>();
		for(int i=10;i<36;i++){
			maps.put(String.valueOf(i),String.valueOf((char)(i+55)));
		}
		return maps;
	}
	
    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is
     *  not empty and not null and not whitespace
     * @since 2.0
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
	
}