package com.mine.myboot.simple.common.base;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

public interface BaseService<M> {

	/**
	 * 主键获取Model
	 * 
	 * @param pk
	 * @return
	 * @author summer
	 * @CT 2017-3-8上午9:28:21
	 */
	public M selectById(Serializable id);

	/**
	 * 获取所有的model
	 * 
	 * @return
	 */
	public List<M> selectAll();

	/**
	 * 分页获取的model
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<M> selectPage(Integer pageNo, Integer pageSize);

	/**
	 * 删除ModelBy主键
	 * 
	 * @param pk
	 * @return
	 * @author summer
	 * @CT 2017-3-8上午9:29:03
	 */
	public boolean deleteById(Serializable id);

	/**
	 * 删除多个ModelBy主键
	 * 
	 * @param pk
	 * @return
	 * @author summer
	 * @CT 2017-3-8上午9:29:03
	 */
	public boolean deleteByIds(Serializable... ids);

	/**
	 * 添加Model
	 * 
	 * @param entity
	 * @return
	 * @author summer
	 * @CT 2017-3-8上午9:29:06
	 */
	public boolean insert(M model);

	/**
	 * 更新ModelById
	 * 
	 * @param entity
	 * @return
	 * @author summer
	 * @CT 2017-3-8上午9:34:24
	 */
	public boolean updateByModel(M model);

}
