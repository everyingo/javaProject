package com.mine.service;

import java.util.List;
import java.util.Set;

import com.mine.model.Right;

public interface RightService extends BaseService<Right>{

	/**
	 * ����/���� Ȩ��
	 * @param model
	 */
	public void saveOrUpdateRight(Right model);

	/**
	 * ���� url���� right
	 * @param url
	 */
	public void createRightByUrl(String url);

	/**
	 * �����޸�Ȩ��
	 * @return
	 */
	public void batchUpdateRight(List<Right> rights);
	
	/**
	 * ��ѯ���� ����rids�� right
	 * @param rights
	 * @return
	 */
	public List<Right> findRigthsInRange(Integer[] rids);

	/**
	 * ��ѯ���в� ����rids�� right
	 * @param rights
	 * @return
	 */
	public List<Right> findRigthsNotInRange(Set<Right> rights);

}
