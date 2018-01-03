package com.mine.myboot.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.myboot.shiro.dao.PermissionDao;
import com.mine.myboot.shiro.pojo.Permission;
import com.mine.myboot.shiro.pojo.PermissionQuery;
import com.mine.myboot.shiro.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> initPermission() {
		PermissionQuery permissionQuery = new PermissionQuery();
		PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
		criteria.andParentIdNotEqualTo(0);
		permissionQuery.setOrderByClause("sort asc");
		return permissionDao.selectByExample(permissionQuery);
	}

	@Override
	public List<Permission> selectPermissionListByUserId(Integer userId) {

		return permissionDao.selectPermissionListByUserId(userId);
	}

}
