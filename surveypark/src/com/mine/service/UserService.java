package com.mine.service;

import java.util.List;
import java.util.Set;

import com.mine.model.Role;
import com.mine.model.User;

public interface UserService extends BaseService<User>{

	/**
	 * �ж������Ƿ���Ч
	 * @param email
	 * @return
	 */
	public boolean isValidatEmail(String email);
	
	/**
	 * ��֤��¼��Ϣ
	 * @param username
	 * @param md5
	 * @return
	 */
	public User validateLoginInfo(String username,String md5);

	/**
	 * �����û���Ȩ
	 * @param model
	 * @param wonRoleIds
	 */
	public void updateAuthorize(User model, Integer[] wonRoleIds);

	/**
	 * ��ѯ ��rids��� ��ɫ
	 * @param roles
	 * @return
	 */
	public List<Role> findRoleInRange(Integer[] rids);
	
	/**
	 * ��ѯ�� ��rids��� ��ɫ
	 * @param roles
	 * @return
	 */
	public List<Role> findRoleNotInRange(Set<Role> roles);

	/**
	 * ����û�����Ȩ
	 * @param uid
	 */
	public void clearUserAuthorize(Integer uid);

}
