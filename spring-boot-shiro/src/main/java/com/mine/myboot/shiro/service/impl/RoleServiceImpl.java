package com.mine.myboot.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.myboot.shiro.dao.RoleDao;
import com.mine.myboot.shiro.pojo.Role;
import com.mine.myboot.shiro.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> selectAll() {
		return roleDao.selectByExample(null);
	}

}
