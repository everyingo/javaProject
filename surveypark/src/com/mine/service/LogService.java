package com.mine.service;

import java.util.List;

import com.mine.model.Log;

public interface LogService extends BaseService<Log>{

	/**
	 * 按照创建时间升序排序查询日志
	 * @return
	 */
	public List<Log> findLogList();

	/**
	 * 创建日志表
	 * @param tableName
	 */
	public void createLogTable(String tableName);

	/**
	 * 查询近几个月的日志
	 * @param n
	 * @return
	 */
	public List<Log> findNearLogList(Integer n);

}
