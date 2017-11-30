package com.mine.myboot.datajpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, unique = true)
	private String account;

	@Column(nullable = false)
	private String nick;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private int pid;

	@Column(columnDefinition = "create_time")
	private int createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", nick=" + nick + ", password=" + password + ", pid=" + pid
				+ ", createTime=" + createTime + "]";
	}

}
