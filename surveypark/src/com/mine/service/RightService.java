package com.mine.service;

import java.util.List;
import java.util.Set;

import com.mine.model.Right;

public interface RightService extends BaseService<Right>{

	/**
	 * 保存/更新 权限
	 * @param model
	 */
	public void saveOrUpdateRight(Right model);

	/**
	 * 更具 url创建 right
	 * @param url
	 */
	public void createRightByUrl(String url);

	/**
	 * 批量修改权限
	 * @return
	 */
	public void batchUpdateRight(List<Right> rights);
	
	/**
	 * 查询所有 包括rids的 right
	 * @param rights
	 * @return
	 */
	public List<Right> findRigthsInRange(Integer[] rids);

	/**
	 * 查询所有不 包括rids的 right
	 * @param rights
	 * @return
	 */
	public List<Right> findRigthsNotInRange(Set<Right> rights);

}
