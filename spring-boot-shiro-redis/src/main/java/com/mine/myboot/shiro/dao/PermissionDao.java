package com.mine.myboot.shiro.dao;

import com.mine.myboot.shiro.pojo.Permission;
import com.mine.myboot.shiro.pojo.PermissionQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionDao {
	int countByExample(PermissionQuery example);

	int deleteByExample(PermissionQuery example);

	int deleteByPrimaryKey(Integer id);

	int insert(Permission record);

	int insertSelective(Permission record);

	List<Permission> selectByExample(PermissionQuery example);

	Permission selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionQuery example);

	int updateByExample(@Param("record") Permission record, @Param("example") PermissionQuery example);

	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);

	List<Permission> selectPermissionListByUserId(Integer userId);
}