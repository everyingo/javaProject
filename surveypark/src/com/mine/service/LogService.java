package com.mine.service;

import java.util.List;

import com.mine.model.Log;

public interface LogService extends BaseService<Log>{

	/**
	 * ���մ���ʱ�����������ѯ��־
	 * @return
	 */
	public List<Log> findLogList();

	/**
	 * ������־��
	 * @param tableName
	 */
	public void createLogTable(String tableName);

	/**
	 * ��ѯ�������µ���־
	 * @param n
	 * @return
	 */
	public List<Log> findNearLogList(Integer n);

}
