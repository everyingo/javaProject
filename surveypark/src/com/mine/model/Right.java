package com.mine.model;

/**
 * Ȩ��
 * @author Administrator
 *
 */
public class Right extends BaseEntity{

	private static final long serialVersionUID = 3651297521170385518L;

	private Integer id;
	
	private String rightName="δ����";
	
	private String rightUrl;
	
	private String rightDesc;
	
	private Long rightCode;//Ȩ��λ��1<<n
	
	private Integer rightPos;//Ȩ���飬�൱��Ȩ�޷��飬��0��ʼ
	
	private Boolean common;

	public Boolean getCommon() {
		return common;
	}

	public void setCommon(Boolean common) {
		this.common = common;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	public String getRightDesc() {
		return rightDesc;
	}

	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}

	public Long getRightCode() {
		return rightCode;
	}

	public void setRightCode(Long rightCode) {
		this.rightCode = rightCode;
	}

	public Integer getRightPos() {
		return rightPos;
	}

	public void setRightPos(Integer rightPos) {
		this.rightPos = rightPos;
	}
	
	
}
