package com.mine.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ½ÇÉ«±í
 * @author Administrator
 *
 */
public class Role extends BaseEntity{

	private static final long serialVersionUID = 2149626817609260312L;

	private Integer id;
	
	private String roleName="Î´ÃüÃû";
	
	private String roleValue;
	
	private String roleDesc;

	private Set<Right> rights=new HashSet<Right>();
	
	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	
}
