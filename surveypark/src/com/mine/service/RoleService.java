package com.mine.service;

import com.mine.model.Role;

public interface RoleService extends BaseService<Role>{

	/**
	 * ����|���� ��ɫ
	 * @param model
	 * @param rIds
	 */
	public void saveOrUpdateRole(Role model, Integer[] rIds);

}
