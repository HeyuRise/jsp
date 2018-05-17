package com.pcbwx.ebes.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.pcbwx.ebes.enums.BaseEnum;

/**
 * 枚举工具类
 * 
 * @author 孙贺宇
 *
 */
public class EnumUtil {

	/**
	 * 根据值获取枚举对象
	 * @param clazz			枚举类
	 * @param mothedName	获取值对应的方法名
	 * @param code  		值
	 * @return
	 */
	public static <T extends Enum<T>> T getEnumByCode(Class<T> clazz,
			String mothedName, String code) {
		T result = null;
		try {
			T[] arr = clazz.getEnumConstants();
			Method method = clazz.getDeclaredMethod(mothedName);
			String typeCode = null;
			for (T t : arr) {
				typeCode = method.invoke(t).toString();
				if (typeCode.contentEquals(code)) {
					result = t;
					break;
				}
			}
		} catch (Exception e) {
			return null;
		}

		return result;
	}

	/**
	 * 转枚举类为集合
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<Object> getList(Class<? extends Enum> clazz) {
		List<Object> list = new ArrayList<Object>();
		if (!BaseEnum.class.isAssignableFrom(clazz)) {
			return list;
		}
		for (Object enumValue : clazz.getEnumConstants()) {
			Object value = ((BaseEnum) enumValue).getDescr();
			list.add(value);
		}
		return list;
	}
}
