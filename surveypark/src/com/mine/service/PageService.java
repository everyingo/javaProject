package com.mine.service;

import com.mine.model.Page;

public interface PageService extends BaseService<Page>{

	/**
	 * ���/���� page
	 * @param model
	 */
    public void saveOrUpdatePage(Page model);

    /**
     * ɾ��Page ͬʱɾ�� Question,Answer 
     * @param pid
     */
	public void deletePage(Integer pid);

	/**
	 * �ƶ�/����  page
	 * @param srcPid
	 * @param targPid
	 * @param pos
	 */
	public void MoveOrCopyPage(Integer srcPid, Integer targPid, Integer pos);
	
	/**
	 * ��ȡǰһҳ
	 * @param pid
	 * @return
	 */
	public Page getPrePage(Integer pid);
	/**
	 * ��ȡ��һҳ
	 * @param pid
	 * @return
	 */
	public Page getNextPage(Integer pid);

}
