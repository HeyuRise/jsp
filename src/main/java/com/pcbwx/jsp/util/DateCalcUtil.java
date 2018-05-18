package com.pcbwx.jsp.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 公共通用date工具包
 * 
 * @author 王海龙
 * @version 1.0
 *
 */
public class DateCalcUtil {

	public static class DateParam {
		private int year;		//获取年份
		private String month;		//获取月份
		private int day;		//获取日
		private int hour;		//小时
		private int minute;		//分           
		private int second;		//秒
		private int WeekOfYear;	//一周的第几天
		
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public int getDay() {
			return day;
		}
		public void setDay(int day) {
			this.day = day;
		}
		public int getHour() {
			return hour;
		}
		public void setHour(int hour) {
			this.hour = hour;
		}
		public int getMinute() {
			return minute;
		}
		public void setMinute(int minute) {
			this.minute = minute;
		}
		public int getSecond() {
			return second;
		}
		public void setSecond(int second) {
			this.second = second;
		}
		public int getWeekOfYear() {
			return WeekOfYear;
		}
		public void setWeekOfYear(int weekOfYear) {
			WeekOfYear = weekOfYear;
		}
	}

	public static DateParam getDateParam(Date date) {
		DateParam param = new DateParam();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		param.setYear(cal.get(Calendar.YEAR));		//获取年份
		String month = DateTimeUtil.date2dateTimeStr(cal.getTime(), "MM");
		param.setMonth(month);					//获取月份
		param.setDay(cal.get(Calendar.DATE));		//获取日
		param.setHour(cal.get(Calendar.HOUR));		//小时
		param.setMinute(cal.get(Calendar.MINUTE));	//分           
		param.setSecond(cal.get(Calendar.SECOND));	//秒
		param.setWeekOfYear(cal.get(Calendar.DAY_OF_WEEK));	//一周的第几天
        
        return param;
	}
	
	/**
	 * 加上一段时间
	 * 
	 * @param opt ： 年->Calendar.YEAR 月->Calendar.MONTH 日->Calendar.DATE 
	 * 				时->Calendar.HOUR 分->Calendar.MINUTE 秒->Calendar.SECOND
	 * 		  time：要增中的时间值
	 * @return 
	 */
	public static java.util.Date addTime(java.util.Date date, int opt, int time) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(opt, time);
		return calendar.getTime();
	}
	/**
	 * 减去一段时间
	 * 
	 * @param 
	 * @return 
	 */
	public static java.util.Date subTime(java.util.Date date, int opt, int time) {
		return addTime(date, opt, time * -1);
	}

	public static Float minuteInteger2HourFloat(Integer src) {
		if (src == null) {
			return null;
		}
		Integer value = (Integer) src;
		value *= 100;
		value /= 60;
		float dstValue = value;
		dstValue /= 100;
		return dstValue;
	}
	public static Integer hourFloat2MinuteInteger(Float src) {
		if (src == null) {
			return null;
		}
		Float value = (Float) src;
		value *= 60;
		return value.intValue();
	}
}
