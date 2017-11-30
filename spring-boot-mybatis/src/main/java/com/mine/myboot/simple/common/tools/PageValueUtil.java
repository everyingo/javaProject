package com.mine.myboot.simple.common.tools;

public class PageValueUtil {

	private static Integer defaultPageNo = 1;

	private static Integer defaultPageSize = 10;

	/**
	 * 
	 * @param pageNo
	 * @return
	 */
	public static int checkPageNumber(Integer pageNo) {
		return (pageNo != null && pageNo > 0) ? pageNo : defaultPageNo;
	}

	/**
	 * 
	 * @param pageSize
	 * @return
	 */
	public static int checkPageSize(Integer pageSize) {
		return (pageSize != null && pageSize > 0) ? pageSize : defaultPageSize;
	}

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static int startRow(Integer pageNo, Integer pageSize) {
		if (pageNo == null)
			pageNo = defaultPageNo;
		if (pageSize == null)
			pageSize = defaultPageSize;
		return (pageNo - 1) * pageSize;
	}
}
