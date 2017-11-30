package com.mine.myboot.simple.common.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseQueryDao<M,Q> extends BaseDao<M> {

	int countByExample(Q example);

	int deleteByExample(Q example);
	
	List<M> selectByExample(Q example);
	
	int updateByExampleSelective(@Param("record") M record, @Param("example") Q example);

	int updateByExample(@Param("record") M record, @Param("example") Q example);
}
