package com.mine.service;

import com.mine.model.Role;

public interface RoleService extends BaseService<Role>{

	/**
	 * 保存|更新 角色
	 * @param model
	 * @param rIds
	 */
	public void saveOrUpdateRole(Role model, Integer[] rIds);

}
