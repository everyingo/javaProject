package com.mine.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.dao.BaseDao;
import com.mine.model.Role;
import com.mine.model.User;
import com.mine.service.RoleService;
import com.mine.service.UserService;
import com.mine.util.MD5Util;
import com.mine.util.StringUtil;
import com.mine.util.ValidateUtil;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	@Autowired
	private BaseDao<Role> roleDao;
	
	@Autowired
	private BaseDao<User> userDao;
	
	@Resource(name="roleServiceImpl")
	private RoleService RoleService;

	@Override
	public void saveEntity(User entity) {
		entity.setPassword(MD5Util.string2MD5(entity.getPassword()));
		super.saveEntity(entity);
	}

	/**
	 * �ж������Ƿ���Ч
	 */
	public boolean isValidatEmail(String email) {
		String hql = "FROM User where email=?";
		List<User> list = this.findEntityByHql(hql, email);
		return ValidateUtil.isValidate(list);
	}

	/**
	 * ��֤��¼��Ϣ
	 */
	public User validateLoginInfo(String username, String md5) {
		String hql = "FROM User u where u.username=? and u.password=?";
		List<User> list = this.findEntityByHql(hql, username, md5);
		return ValidateUtil.isValidate(list) ? list.get(0) : null;
	}

	/**
	 * �����û���Ȩ
	 */
	public void updateAuthorize(User model, Integer[] wonRoleIds) {
		if (!ValidateUtil.isValidate(wonRoleIds)) {
			model.getRoles().clear();
		} else {
			List<Role> rlist = this.findRoleInRange(wonRoleIds);
			model.setRoles(new HashSet<Role>(rlist));
		}
		this.saveOrUpdateEntity(model);
	}

	/**
	 * ��ѯ ��rids��� ��ɫ
	 * 
	 * @param roles
	 * @return
	 */
	public List<Role> findRoleInRange(Integer[] rids) {
		if (ValidateUtil.isValidate(rids)) {
			String hql = "from Role r where r.id in( "
					+ StringUtil.arr2str(rids) + " )";
			return roleDao.findEntityByHql(hql);
		}
		return null;
	}

	/**
	 * ��ѯ�� ��rids��� ��ɫ
	 * 
	 * @param roles
	 * @return
	 */
	public List<Role> findRoleNotInRange(Set<Role> roles) {
		if (ValidateUtil.isValidate(roles)) {
			String hql = "from Role r where r.id not in( " + changeSet2str(roles)
					+ " )";
			return roleDao.findEntityByHql(hql);
		}else{
			return RoleService.findAllEntities();
		}
	}

	private String changeSet2str(Set<Role> roles) {
		String str = "";
		if (ValidateUtil.isValidate(roles)) {
			for (Role r : roles) {
				str = str + r.getId() + ",";
			}
			return str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * ����û�����Ȩ
	 * @param model
	 */
	public void clearUserAuthorize(Integer uid){
		User u=userDao.getEntity(uid);
		u.getRoles().clear();
	}
}
