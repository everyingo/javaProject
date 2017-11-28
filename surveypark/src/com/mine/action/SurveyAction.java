package com.mine.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.aware.UserAware;
import com.mine.model.Survey;
import com.mine.model.User;
import com.mine.service.SurveyService;
import com.mine.util.Constants;
import com.mine.util.ValidateUtil;

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware{

	
	private static final long serialVersionUID = 3726396031326825211L;
	
	@Resource(name="surveyServiceImpl")
	private SurveyService surveyService;
	
	private List<Survey> surveys;
	
	/**���ݲ���*/
	private Integer sid;

	/**
	 * UserAware �ӿ� ����LoginInterceper ��ע��User
	 */
	private User user;
	
	/**��̬ �쳣��תҳ��*/
	private String dynamiErrorPage;
	
	public String getDynamiErrorPage() {
		return dynamiErrorPage;
	}
	public void setDynamiErrorPage(String dynamiErrorPage) {
		this.dynamiErrorPage = dynamiErrorPage;
	}
	public List<Survey> getSurveys() {
		return surveys;
	}
	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	public File getLogoPhoto() {
		return logoPhoto;
	}
	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}
	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}
	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}
	/**
	 * �ҵĵ���
	 * @return
	 */
	public String mySurveys(){
		surveys=surveyService.getMySurveys(user);
		return "mySurveyListPage";
	}
	/**
	 * �½�����
	 * @return
	 */
	public String newSurvey(){
		this.model=surveyService.newSurvey(user);
		return "mySurveyDesignPage";
	}
	/**
	 * ��Ƶ���
	 * 
	 * �˴���model ��ֵվ���滻���⣺
	 *     ����struts�����л��ƣ�StrutsPrepareAndExecuteFilter ������Action Proxy�Ĵ����� �����ȳ�ʼ����ǰAction
	 *     ������BaseActionҲ���ʼ�������Զ�Ӧ����model Ҳ���ʼ�� new T() => model1, ����struts������������˳��
	 *     LoginInterceter... ModelDriven .. ������ModelDriven��������ʱ�������getModel()���� ��model1 ����ֵջ
	 *     ֮�����Action��designSurvey() ��ʱmodel ���±���ֵ xx.get() model2��ֻ������ʱAction �Ѳ���ջ������ջ�����Ƕ�model1��
	 *     ���á������� ҳ����ջ����model ʱû�дﵽԤ�ڵ�Ч����
	 *     
	 *     ���������
	 *     1.�ֶ�ѹջ����϶ȱȽϸߣ����Ƽ�֮�����ջ��ջ�еĶ���Ƚ϶ࣩ
	 *     2.�ֶ�����ģ�͵�����ȫ����ֵ����ģ�ͣ����ܱȽϲ
	 *     3.ʹ��paramsPrepareParamsStack+preparable���������ʹ��
	 *       prepare��������ִ�У���Ϊmodel��ֵ �����modelDriven��getModel()����
	 *       ��modelѹ��ֵջ��������Ҫ��prepare���������в������ݣ�defaultstack��
	 *       prepare������ɲ����Ĵ��ݣ���˿��ԸĻ���paramsPrepareParamsStack���ﵽ��Ŀ�ġ�
	 *       (�˷��������action�ķ������ӣ�������ά��)
	 *     4.ͨ������  modelDriven.refreshModelBeforeResult Ϊtrue ������ ջ��model�ĸ��µļ���
	 *       �Ӷ�ʵ�ֽ����µ�model ����ջ���С�
	 * @return
	 */
	public String designSurvey(){
		this.model=surveyService.getSurveyAndChildren(sid);
		return "mySurveyDesignPage";
	}
	
	
	/**
	 * ȥ�༭survey����
	 */
	public String editSurvey(){
		this.model=surveyService.getSurveyById(sid);
		return "toEditSurveyPage";
	}
	/**
	 * ����survey����
	 * @return
	 */
	public String updateSurvey(){
		this.sid=model.getId();
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "toDesignSurveyAction";
	}
	/**
	 * ɾ������ Survey ͬʱɾ�� page,question,answer 
	 * @return �ҵĵ���
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "mySurveyAction";
	}
	/**
	 * �������
	 * @return
	 */
	public String clearSurvey(){
		surveyService.clearSurvey(sid);
		return "mySurveyAction";
	}
	/**
	 * ��/�ر� ����
	 * @return
	 */
	public String toggleStatus(){
		surveyService.toggleStatus(sid);
		return "mySurveyAction";
	}
	/**
	 * ��������
	 * @return
	 */
	public String analyzeSurvey(){
		this.model=surveyService.getSurveyAndChildren(sid);
		return "toAnalyzeSurveyPage";
	}
	/**
	 * ��������logoҳ��
	 * @return
	 */
	public String toAddLogoPage(){
		return "toAddLogoPage";
	}
	/**
	 * �ļ��ϴ�
	 */
	private File logoPhoto;
	private String logoPhotoFileName;
	public String doAddLogo(){
		//����ļ���Ч
		if(ValidateUtil.isValidate(logoPhotoFileName)){
			//1.�����ļ�����
			String dir=getServletContext().getRealPath(Constants.UploadLogoPhoth);
			String ext=logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			//���ϵͳ������ʱ��
			Long l=System.nanoTime();
			String newFileName=l+ext;
			File newFile=new File(dir, newFileName);
			logoPhoto.renameTo(newFile);
			String relativePath=Constants.UploadLogoPhoth+File.separator+newFileName;
			//2.�����ļ�·��
			surveyService.updateLogoPhotoPath(sid,relativePath);
		}
		return "toDesignSurveyAction";
	}
	
	/**
	 * ��DoAddLogo ����֮ǰ���ã���̬���� �ļ��ϴ��쳣ʱ��ת��ҳ��
	 * @throws Exception
	 */
	public void prepareDoAddLogo() throws Exception {
		dynamiErrorPage="/toAddLogo.jsp";
		super.prepare();
	}
	/**
	 * �ж��ļ��Ƿ����
	 */
	public boolean isLogoPhotoExists(){
		if(ValidateUtil.isValidate(model.getLogoPhotoPath())){
			String realPath=getServletContext().getRealPath(model.getLogoPhotoPath());
			File file=new File(realPath);
			return file.exists();
		}
		return false;
	}
	/**
	 * modelDriven ǰ���ô˷���
	 * @throws Exception
	 */
	/*public void prepareDesignSurvey() throws Exception {
		this.model=surveyService.getSurveyAndChildren(sid);
	}*/
	/**
	 * ע��User
	 */
	public void setUser(User user) {
		this.user=user;
	}
	

}
