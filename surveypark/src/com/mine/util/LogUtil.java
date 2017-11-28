package com.mine.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;

/**
 * ��־������
 * 
 * @author Administrator
 * 
 */
public class LogUtil {

	/**
	 * ������־����
	 * 
	 * @param offset
	 * @return
	 * @throws ParseException 
	 */
	public static String createLogTableName(int offset){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1 + offset;
		if (month > 12) {
			year++;
			month = month - 12;
		}else if (month < 1) {
			year--;
			month = month + 12;
		}
		DecimalFormat df=new DecimalFormat();
		df.applyPattern("00");
		return "logs_" + year + "_" + df.format(month);
	}
}
