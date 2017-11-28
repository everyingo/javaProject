package com.mine.service;

import com.mine.model.Page;

public interface PageService extends BaseService<Page>{

	/**
	 * 添加/更新 page
	 * @param model
	 */
    public void saveOrUpdatePage(Page model);

    /**
     * 删除Page 同时删除 Question,Answer 
     * @param pid
     */
	public void deletePage(Integer pid);

	/**
	 * 移动/复制  page
	 * @param srcPid
	 * @param targPid
	 * @param pos
	 */
	public void MoveOrCopyPage(Integer srcPid, Integer targPid, Integer pos);
	
	/**
	 * 获取前一页
	 * @param pid
	 * @return
	 */
	public Page getPrePage(Integer pid);
	/**
	 * 获取后一页
	 * @param pid
	 * @return
	 */
	public Page getNextPage(Integer pid);

}
