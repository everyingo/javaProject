package com.mine.myboot.simple.common.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mine.myboot.simple.common.tools.PageValueUtil;
import com.mine.myboot.simple.common.tools.ValidateUtil;

public class BaseServiceImpl<M> implements BaseService<M> {

	@Autowired
	private BaseDao<M> baseDao;

	@Override
	public M selectById(Serializable id) {
		if (null == id)
			return null;
		return baseDao.selectByPrimaryKey(id);
	}

	@Override
	public List<M> selectAll() {
		return baseDao.selectByExample();
	}

	@Override
	public PageInfo<M> selectPage(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(PageValueUtil.checkPageNumber(pageNo), PageValueUtil.checkPageSize(pageSize));
		return new PageInfo<M>(baseDao.selectByExample());
	}

	@Override
	public boolean deleteById(Serializable id) {
		if (null == id)
			return false;
		return baseDao.deleteByPrimaryKey(id) > 0;
	}

	@Override
	@Transactional
	public boolean deleteByIds(Serializable... ids) {
		if (!ValidateUtil.isValidate(ids))
			return false;
		int count = 0;
		for (Serializable id : ids) {
			if (deleteById(id)) {
				count++;
			}
		}
		return count > 0;
	}

	@Override
	public boolean insert(M model) {
		return baseDao.insertSelective(model) > 0;
	}

	@Override
	public boolean updateByModel(M model) {
		return baseDao.updateByPrimaryKeySelective(model) > 0;
	}

}
