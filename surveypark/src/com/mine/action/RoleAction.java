package com.mine.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Right;
import com.mine.model.Role;
import com.mine.service.RightService;
import com.mine.service.RoleService;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	
	private static final long serialVersionUID = 4979456560452538106L;
	
	@Resource(name="roleServiceImpl")
	private RoleService roleService;
	
	@Resource(name="rightServiceImpl")
	private RightService rightService;
	
	private Integer roleId;
	
	private List<Right> noWonRights;
	
	private Integer[] wonRightIds;
	
	private List<Role> roles;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<Right> getNoWonRights() {
		return noWonRights;
	}

	public void setNoWonRights(List<Right> noWonRights) {
		this.noWonRights = noWonRights;
	}
	

	public Integer[] getWonRightIds() {
		return wonRightIds;
	}

	public void setWonRightIds(Integer[] wonRightIds) {
		this.wonRightIds = wonRightIds;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * 角色列表
	 * @return
	 */
	public String toRoleListPage(){
		this.roles=roleService.findAllEntities();
		return "toRoleListPage";
	}
	/**
	 * 添加角色
	 * @return
	 */
	public String toAddRolePage(){
		this.noWonRights=rightService.findAllEntities();
		return "toAddRolePage";
	}
	/**
	 * 编辑角色
	 * @return
	 */
	public String toEditRolePage(){
		this.model=roleService.getEntity(roleId);
		this.noWonRights=rightService.findRigthsNotInRange(model.getRights());
		return "toEditRolePage";
	}
	/**
	 * 保存|更新 角色
	 * @return
	 */
	public String saveOrUpdateRole(){
		roleService.saveOrUpdateRole(this.model,wonRightIds);
		return "roRoleListAction";
	}

}
