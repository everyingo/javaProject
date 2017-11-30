package com.mine.myboot.simple.common.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<M> {

	int deleteByPrimaryKey(Serializable id);

	int insert(M record);

	int insertSelective(M record);

	M selectByPrimaryKey(Serializable id);

	List<M> selectByExample();

	int updateByPrimaryKeySelective(M record);

	int updateByPrimaryKey(M record);
}
