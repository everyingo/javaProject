package com.mine.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Survey extends BaseEntity{


	private static final long serialVersionUID = -3961327296735852333L;

	private Integer id;
	
	private String title="未命名";
	
	private String preText="上一步";
	
	private String nextText="下一步";
	
	private String exitText="退出";
	
	private String doneText="完成";
	//是否开放
	private Boolean closed;
	
	private String logoPhotoPath;
	
	private Date createTime=new Date();
	
	private User user;
	private Set<Page> pages=new HashSet<Page>();
	
	private Float minOrderno;
	
	private Float maxOrderno;
	
	public Float getMinOrderno() {
		return minOrderno;
	}

	public void setMinOrderno(Float minOrderno) {
		this.minOrderno = minOrderno;
	}

	public Float getMaxOrderno() {
		return maxOrderno;
	}

	public void setMaxOrderno(Float maxOrderno) {
		this.maxOrderno = maxOrderno;
	}

	public String getLogoPhotoPath() {
		return logoPhotoPath;
	}

	public void setLogoPhotoPath(String logoPhotoPath) {
		this.logoPhotoPath = logoPhotoPath;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreText() {
		return preText;
	}

	public void setPreText(String preText) {
		this.preText = preText;
	}

	public String getNextText() {
		return nextText;
	}

	public void setNextText(String nextText) {
		this.nextText = nextText;
	}

	public String getExitText() {
		return exitText;
	}

	public void setExitText(String exitText) {
		this.exitText = exitText;
	}

	public String getDoneText() {
		return doneText;
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
