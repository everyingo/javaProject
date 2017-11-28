package com.mine.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Role;
import com.mine.model.User;
import com.mine.service.UserService;

/**
 * �û���Ȩ
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {

	
	private static final long serialVersionUID = -7281499855539652L;
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	private Integer uid;
	
	private List<User> users;
	
	private List<Role> noWonRoles;
	
	private Integer[] wonRoleIds;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Role> getNoWonRoles() {
		return noWonRoles;
	}
	public void setNoWonRoles(List<Role> noWonRoles) {
		this.noWonRoles = noWonRoles;
	}
	public Integer[] getWonRoleIds() {
		return wonRoleIds;
	}
	public void setWonRoleIds(Integer[] wonRoleIds) {
		this.wonRoleIds = wonRoleIds;
	}
	/**
	 * �û��б�
	 * @return
	 */
	public String userListPage(){
		this.users=userService.findAllEntities();
		return "userListPage";
	}
	
	/**
	 * �û���Ȩ
	 * @return
	 */
	public String userAuthorize(){
		this.model=userService.getEntity(uid);
		this.noWonRoles=userService.findRoleNotInRange(model.getRoles());
		System.out.println("-->"+noWonRoles.size());
		return "userAuthorizePage";
	}
	/**
	 * �����û���Ȩ
	 * @return
	 */
	public String updateAuthorize(){
		userService.updateAuthorize(model,wonRoleIds);
		return "userListAction";
	}
	/**
	 * ����û�����Ȩ
	 * @return
	 */
	public String clearUserAuthorize(){
		userService.clearUserAuthorize(uid);
		return "userListAction";
	}

}
