package com.heyu.jsp.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataUtil {
	public static boolean equals(Object value1, Object value2) {
		if (value1 == null && value2 == null) {
			return true;
		}
		if (value1 == null || value2 == null) {
			return false;
		}
		return value1.equals(value2);
	}
	/**
	 * 去除List中的重复对象
	 * 
	 * @param lists
	 * @return
	 */
	public static <T> List<T> RemoveDuplicate(List<T> lists) {
		List<T> results = new ArrayList<T>();
		Set<T> sets = new HashSet<T>(lists);
		results.addAll(sets);
		return results;
	}

	/**
	 * 去除List中的重复对象
	 * 
	 * @param lists
	 * @return
	 */
	 public static List<String> listString2Upper(List<String> lists) {
		 if (lists == null) {
			 return null;
		 }
		 List<String> results = new ArrayList<String>();
		 for (String str : lists) {
			 results.add(str.toUpperCase());
		 }
		 return results;
	 }

	/**
	 * 将父类的数据通过字段反射拷贝到子类中
	 * 
	 * @param father
	 *            父类实例
	 * @param childClass
	 *            子类类型
	 * @return 子类实例
	 */
	public static Object fatherToChild(Object father, Class<?> childClass) {
		// if(!(child.getClass().getSuperclass()==father.getClass())){
		if (!(childClass.getSuperclass() == father.getClass())) {
			System.err.println("child不是father的子类");
		}
		Class<? extends Object> fatherClass = father.getClass();
		// Class childClass= child.getClass();
		Field ff[] = fatherClass.getDeclaredFields();
		Object child = null;
		try {
			child = childClass.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		for (int i = 0; i < ff.length; i++) {
			Field field = ff[i];// 取出每一个属性，如deleteDate
			Class<?> type = field.getType();
			try {
				// 方法getDeleteDate
				String upperFieldName = StringUtil
						.toUpperFirst(field.getName());
				Method m = fatherClass.getMethod("get" + upperFieldName);
				Object objValue = m.invoke(father);// 取出属性值

				Method mSet = childClass
						.getMethod("set" + upperFieldName, type);
				mSet.invoke(child, objValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return child;
	}

	public static <T> List<T> set2list(Set<T> set) {
		if (set == null) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (T item : set) {
			list.add(item);
		}
		return list;
	}

	public static <T1, T2> Map<T1, T2> list2map(List<T2> list,
			Class<?> dstClazz, String keyName) throws NoSuchMethodException,
			SecurityException {
		Map<T1, T2> map = new HashMap<T1, T2>();
		if (list == null || list.isEmpty()) {
			return map;
		}

		String srcUpperFieldName = StringUtil.toMethodName(keyName);
		Method mGet = dstClazz.getMethod("get" + srcUpperFieldName);
		if (mGet == null) {
			return map;
		}
		for (T2 item : list) {
			Object srcValue = null;
			try {
				srcValue = mGet.invoke(item);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 取出属性值
			if (srcValue == null) {
				continue;
			}
			@SuppressWarnings("unchecked")
			T1 dstValue = (T1) srcValue;

			map.put(dstValue, item);
		}
		return map;
	}
	
	public static <T1, T2> Map<T1, T2> set2map(Set<T2> set,
			Class<?> dstClazz, String keyName) throws NoSuchMethodException,
			SecurityException {
		Map<T1, T2> map = new HashMap<T1, T2>();
		if (set == null || set.isEmpty()) {
			return map;
		}

		String srcUpperFieldName = StringUtil.toMethodName(keyName);
		Method mGet = dstClazz.getMethod("get" + srcUpperFieldName);
		if (mGet == null) {
			return map;
		}
		for (T2 item : set) {
			Object srcValue = null;
			try {
				srcValue = mGet.invoke(item);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 取出属性值
			if (srcValue == null) {
				continue;
			}
			@SuppressWarnings("unchecked")
			T1 dstValue = (T1) srcValue;

			map.put(dstValue, item);
		}
		return map;
	}

	public static <T1, T2> Map<T1, List<T2>> list2mapList(List<T2> list,
			Class<?> dstClazz, String keyName) throws NoSuchMethodException,
			SecurityException {
		Map<T1, List<T2>> map = new HashMap<T1, List<T2>>();
		if (list == null || list.isEmpty()) {
			return map;
		}

		String srcUpperFieldName = StringUtil.toMethodName(keyName);
		Method mGet = dstClazz.getMethod("get" + srcUpperFieldName);
		if (mGet == null) {
			return map;
		}
		for (T2 item : list) {
			Object srcValue = null;
			try {
				srcValue = mGet.invoke(item);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 取出属性值
			if (srcValue == null) {
				continue;
			}
			@SuppressWarnings("unchecked")
			T1 dstValue = (T1) srcValue;

			List<T2> items = map.get(dstValue);
			if (items == null) {
				items = new ArrayList<T2>();
				map.put(dstValue, items);
			}
			items.add(item);
		}
		return map;
	}

	public static <T1, T2> List<T1> mapKey2list(Map<T1, T2> map)
			throws NoSuchMethodException, SecurityException {
		List<T1> list = new ArrayList<T1>();

		for (Map.Entry<T1, T2> entry : map.entrySet()) {
			list.add(entry.getKey());
		}
		return list;
	}

	public static <T1, T2> List<T2> mapValue2list(Map<T1, T2> map)
			throws NoSuchMethodException, SecurityException {
		List<T2> list = new ArrayList<T2>();

		for (Map.Entry<T1, T2> entry : map.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	public static <T1, T2> List<T1> fetchField2list(List<T2> list,
			Class<?> dstClazz, String keyName) throws NoSuchMethodException,
			SecurityException {
		List<T1> keyList = new ArrayList<T1>();
		if (list == null || list.isEmpty()) {
			return keyList;
		}

		String srcUpperFieldName = StringUtil.toMethodName(keyName);
		Method mGet = dstClazz.getMethod("get" + srcUpperFieldName);
		if (mGet == null) {
			return keyList;
		}
		for (T2 item : list) {
			Object srcValue = null;
			try {
				srcValue = mGet.invoke(item);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 取出属性值
			if (srcValue == null) {
				continue;
			}
			@SuppressWarnings("unchecked")
			T1 dstValue = (T1) srcValue;

			keyList.add(dstValue);
		}
		return keyList;
	}

	public static <T1, T2> List<T1> fetchField2listNoDuplicate(List<T2> list,
			Class<?> dstClazz, String keyName) throws NoSuchMethodException,
			SecurityException {
		if (list == null || list.isEmpty()) {
			return new ArrayList<T1>();
		}

		String srcUpperFieldName = StringUtil.toMethodName(keyName);
		Method mGet = dstClazz.getMethod("get" + srcUpperFieldName);
		if (mGet == null) {
			return new ArrayList<T1>();
		}
		Set<T1> keySet = new HashSet<T1>();
		for (T2 item : list) {
			Object srcValue = null;
			try {
				srcValue = mGet.invoke(item);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 取出属性值
			if (srcValue == null) {
				continue;
			}
			@SuppressWarnings("unchecked")
			T1 dstValue = (T1) srcValue;

			keySet.add(dstValue);
		}
		return set2list(keySet);
	}

	public static <T> List<List<T>> subList(List<T> list, int subNum) {
		List<List<T>> subListList = new ArrayList<List<T>>();
		int fromIndex = 0;
		do {
			int toIndex = fromIndex + subNum;
			if (toIndex > list.size()) {
				toIndex = list.size();
			}
			if (toIndex <= fromIndex) {
				break;
			}
			final List<T> subList = list.subList(fromIndex, toIndex);
			fromIndex += subList.size();
			subListList.add(subList);
		} while (true);
		return subListList;
	}

	public static <T> List<List<T>> subList(Set<T> set, int subNum) {
		List<T> list = set2list(set);
		return subList(list, subNum);
	}

	public static Map<String, Object> fetchField2list(Object obj,
			Class<?> dstClazz) throws NoSuchMethodException, SecurityException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}

		Class<? extends Object> fatherClass = obj.getClass();
		// Class childClass= child.getClass();
		Field ff[] = fatherClass.getDeclaredFields();
		for (int i = 0; i < ff.length; i++) {
			Field f = ff[i];// 取出每一个属性，如deleteDate
			try {
				// 方法getDeleteDate
				String upperFieldName = StringUtil.toMethodName(f.getName());
				Method m = fatherClass.getMethod("get" + upperFieldName);
				Object objValue = m.invoke(obj);// 取出属性值

				map.put(f.getName(), objValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

}
