package com.mine.core.common.tools;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("all")
public class DateUtil {

	public static final String yyyy = "yyyy";

	public static final String MM = "MM";

	public static final String dd = "dd";

	public static final String yyyyMMdd = "yyyyMMdd";

	public static final String yyyyMMddHHmm = "yyyyMMddHHmm";

	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

	public static final String yyMMddHHmmssSSS = "yyMMddHHmmssSSS";

	public static final String yyyy_MM_dd = "yyyy-MM-dd";

	public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	public static final String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss:SSS";

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 加减时间（天）
	 * 
	 * @param day
	 * @return
	 */
	public static Date changeByDay(Date date, Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	/**
	 * 加减时间（天）
	 * 
	 * @param day
	 * @return
	 */
	public static Date changeByDay(Integer day) {
		return changeByDay(getCurrentDate(), day);
	}

	/**
	 * 加减时间（月）
	 * 
	 * @param day
	 * @return
	 */
	public static Date changeByMonth(Date date, Integer month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	/**
	 * 加减时间（月）
	 * 
	 * @param month
	 * @return
	 */
	public static Date changeByMonth(Integer month) {
		return changeByMonth(getCurrentDate(), month);
	}

	/**
	 * 时间精确到（天）舍去（时分秒）
	 * 
	 * @param date
	 * @return
	 */
	public static Date accurateDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd);
		String dateString = sdf.format(date.getTime());
		ParsePosition pos = new ParsePosition(0);
		Date after = sdf.parse(dateString, pos);
		return after;
	}

	/**
	 * 时间格式字符串转时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str, String format) {
		if (!ValidateUtil.isValidate(str))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间转时间格式字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date == null) {
			date = getCurrentDate();
		}
		if (!ValidateUtil.isValidate(format)) {
			format = yyyy_MM_dd_HH_mm;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * 当前时间转时间格式字符串
	 * 
	 * @param format
	 * @return
	 */
	public static String currDate2Str(String format) {
		return date2Str(getCurrentDate(), format);
	}

	/**
	 * 时间转时间格式字符串（默认格式）
	 * 
	 * @return
	 */
	public static String currDate2DefaultStr() {
		return date2Str(getCurrentDate(), yyyy_MM_dd_HH_mm);
	}

	/**
	 * 时间戳字符串转时间
	 * 
	 * @param stamp
	 * @return
	 */
	public static Date stamp2Date(String stamp) {
		if (!ValidateUtil.isValidate(stamp)) {
			return null;
		}
		long lt = new Long(stamp);
		return new Date(lt);
	}

	/**
	 * 时间转时间戳字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Stamp(Date date) {
		if (date == null) {
			return String.valueOf(System.currentTimeMillis());
		}
		return String.valueOf(date.getTime());
	}

	/**
	 * 获取当月第一天的时间（时分秒）
	 * 
	 * @return
	 */
	public static Date getCurrMonthFirstDate() {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.MONTH, 0);
		cd.set(Calendar.DAY_OF_MONTH, 1);
		return cd.getTime();
	}

	/**
	 * 
	 * 获取下个月第一天的时间（时分秒）
	 */
	public static Date getNextMonthFirstDate() {
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.MONTH, 1);
		cd.set(Calendar.DAY_OF_MONTH, 1);
		return cd.getTime();
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(date2Str(getNextMonthFirstDate(), null));
	}

}
