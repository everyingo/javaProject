package com.mine.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.dao.BaseDao;
import com.mine.model.Right;
import com.mine.model.Role;
import com.mine.service.RightService;
import com.mine.service.RoleService;
import com.mine.util.ValidateUtil;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Resource(name="rightServiceImpl")
	private RightService rightService;
	
	@Autowired
	private BaseDao<Role> roleDao;
	/**
	 * 保存|更新 角色
	 * @param model
	 * @param rIds
	 */
	public void saveOrUpdateRole(Role model, Integer[] rIds){
		if(!ValidateUtil.isValidate(rIds)){
			model.getRights().clear();
		}else{
			List<Right> rlist=rightService.findRigthsInRange(rIds);
		    model.setRights(new HashSet<Right>(rlist));
			
		}
		roleDao.saveOrUpdateEntity(model);
	}
}
