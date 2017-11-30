package com.mine.myboot.simple.common.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

	/**
	 * log info
	 * 
	 * @param content
	 * @param clasz
	 */
	public static void info(String content, Class<?> clasz) {
		Logger logger = LoggerFactory.getLogger(clasz);
		logger.info(content);
	}

	/**
	 * log error
	 * 
	 * @param content
	 * @param clasz
	 */
	public static void error(String content, Class<?> clasz) {
		Logger logger = LoggerFactory.getLogger(clasz);
		logger.error(content);
	}
}
