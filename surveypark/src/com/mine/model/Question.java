package com.mine.model;



import com.mine.util.StringUtil;

public class Question extends BaseEntity{

	private static final long serialVersionUID = 4579289775558877631L;

	private static final String RN = "\r\n";

	private Integer id;
	
	//����0-8
	private Integer questionType;
	
	private String title;
	
	//ѡ��
	private String options;
	private String[] optionArr;
	
	//������
	private Boolean other;
	
	//��������ʽ��0-�� 1-�ı��� 2-�����б�
	private Integer otherStyle;
	
	//����������ѡ��
	private String otherSelectOptions;
	private String[] otherSelectOptionArr;
	
	//�����б��⼯
	private String matrixRowTitles;
	private String[] matrixRowTitleArr;
	
	//�����б��⼯
	private String matriColTitles;
	private String[] matriColTitleArr;
	
	//����������ѡ�
	private String matriSelectOptions;
	private String[] matriSelectOptionArr;
	
	private Page page;
	
	

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
		this.optionArr=StringUtil.string2Arr(options, RN);
	}

	public Boolean getOther() {
		return other;
	}

	public void setOther(Boolean other) {
		this.other = other;
	}

	public Integer getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(Integer otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
		this.otherSelectOptionArr=StringUtil.string2Arr(otherSelectOptions, RN);
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
		this.matrixRowTitleArr=StringUtil.string2Arr(matrixRowTitles, RN);
	}

	public String getMatriColTitles() {
		return matriColTitles;
	}

	public void setMatriColTitles(String matriColTitles) {
		this.matriColTitles = matriColTitles;
		this.matriColTitleArr=StringUtil.string2Arr(matriColTitles, RN);
	}

	public String getMatriSelectOptions() {
		return matriSelectOptions;
	}

	public void setMatriSelectOptions(String matriSelectOptions) {
		this.matriSelectOptions = matriSelectOptions;
		this.matriSelectOptionArr=StringUtil.string2Arr(matriSelectOptions, RN);
	}

	public String[] getOptionArr() {
		return optionArr;
	}

	public void setOptionArr(String[] optionArr) {
		this.optionArr = optionArr;
	}

	public String[] getOtherSelectOptionArr() {
		return otherSelectOptionArr;
	}

	public void setOtherSelectOptionArr(String[] otherSelectOptionArr) {
		this.otherSelectOptionArr = otherSelectOptionArr;
	}

	public String[] getMatrixRowTitleArr() {
		return matrixRowTitleArr;
	}

	public void setMatrixRowTitleArr(String[] matrixRowTitleArr) {
		this.matrixRowTitleArr = matrixRowTitleArr;
	}

	public String[] getMatriColTitleArr() {
		return matriColTitleArr;
	}

	public void setMatriColTitleArr(String[] matriColTitleArr) {
		this.matriColTitleArr = matriColTitleArr;
	}

	public String[] getMatriSelectOptionArr() {
		return matriSelectOptionArr;
	}

	public void setMatriSelectOptionArr(String[] matriSelectOptionArr) {
		this.matriSelectOptionArr = matriSelectOptionArr;
	}
	
	
}
