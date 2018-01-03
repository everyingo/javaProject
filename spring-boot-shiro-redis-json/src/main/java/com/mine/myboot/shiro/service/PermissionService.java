package com.mine.myboot.shiro.service;

import java.util.List;

import com.mine.myboot.shiro.pojo.Permission;

public interface PermissionService {

	/**
	 * 初始化shiro权限列表
	 * 
	 * @return
	 */
	List<Permission> initPermission();

	/**
	 * 查询权限列表By用户Id
	 * 
	 * @param id
	 * @return
	 */
	List<Permission> selectPermissionListByUserId(Integer userId);
}
