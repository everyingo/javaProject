package com.mine.service;

import java.util.List;
import java.util.Set;

import com.mine.model.Role;
import com.mine.model.User;

public interface UserService extends BaseService<User>{

	/**
	 * 判断邮箱是否有效
	 * @param email
	 * @return
	 */
	public boolean isValidatEmail(String email);
	
	/**
	 * 验证登录信息
	 * @param username
	 * @param md5
	 * @return
	 */
	public User validateLoginInfo(String username,String md5);

	/**
	 * 更新用户授权
	 * @param model
	 * @param wonRoleIds
	 */
	public void updateAuthorize(User model, Integer[] wonRoleIds);

	/**
	 * 查询 在rids里的 角色
	 * @param roles
	 * @return
	 */
	public List<Role> findRoleInRange(Integer[] rids);
	
	/**
	 * 查询不 在rids里的 角色
	 * @param roles
	 * @return
	 */
	public List<Role> findRoleNotInRange(Set<Role> roles);

	/**
	 * 清空用户的授权
	 * @param uid
	 */
	public void clearUserAuthorize(Integer uid);

}
