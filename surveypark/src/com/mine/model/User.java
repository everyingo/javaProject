package com.mine.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User extends BaseEntity{

	private static final long serialVersionUID = 4057108903442262953L;

	private Integer id;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private String nickName;
	
	private Date createTime=new Date();
	
	private Set<Role> roles=new HashSet<Role>();
	
	/***/
	private long[] rightSum;
	
	private boolean superAdmin;
	
	public long[] getRightSum() {
		return rightSum;
	}

	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * �����û�Ȩ���ܺ�
	 */
	public void calculateRightSum(){
		int pos=0;
		long code=0;
		getMaxPos();
		for(Role role:this.roles){
			/*** �жϸ��û� �Ƿ�ӵ�г�������Ա ��ɫ*/
			if("-1".equals(role.getRoleValue())){
				this.setSuperAdmin(true);
				//�ͷ���Դ
				this.roles=null;
			}
			for(Right right:role.getRights() ){
				pos=right.getRightPos();
				code=right.getRightCode();
				rightSum[pos]=rightSum[pos] | code;
			}
		}
		//�ͷ���Դ
		this.roles=null;
	}
	private void getMaxPos(){
		int pos=-1;
		for(Role role:this.roles){
			for(Right right:role.getRights()){
		        int curpos=right.getRightPos();
		        if(curpos>pos){
		        	pos=curpos;
		        }
			}
		}
		rightSum=new long[pos+1];
	}

	/**
	 * �ж��û��Ƿ���Ȩ��
	 * @param right
	 * @return
	 */
	public boolean hasRight(Right right) {
		int pos=right.getRightPos();
		long code=right.getRightCode();
		return (rightSum[pos] & code )!=0;
	}
}
