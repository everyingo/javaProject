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
	
	/**传递参数*/
	private Integer sid;

	/**
	 * UserAware 接口 会在LoginInterceper 中注入User
	 */
	private User user;
	
	/**动态 异常跳转页面*/
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
	 * 我的调查
	 * @return
	 */
	public String mySurveys(){
		surveys=surveyService.getMySurveys(user);
		return "mySurveyListPage";
	}
	/**
	 * 新建调查
	 * @return
	 */
	public String newSurvey(){
		this.model=surveyService.newSurvey(user);
		return "mySurveyDesignPage";
	}
	/**
	 * 设计调查
	 * 
	 * 此处的model 在值站的替换问题：
	 *     由于struts的运行机制，StrutsPrepareAndExecuteFilter 产生的Action Proxy的代理类 会首先初始化当前Action
	 *     以至于BaseAction也会初始化，所以对应事务model 也会初始化 new T() => model1, 按照struts的拦截器调用顺序
	 *     LoginInterceter... ModelDriven .. 当调用ModelDriven的拦截器时，会调用getModel()方法 将model1 放入值栈
	 *     之后调用Action的designSurvey() 此时model 重新被赋值 xx.get() model2，只不过此时Action 已不在栈顶，在栈顶的是对model1的
	 *     引用。以至于 页面用栈顶的model 时没有达到预期的效果。
	 *     
	 *     解决方案：
	 *     1.手动压栈（耦合度比较高，不推荐之间操作栈，栈中的对象比较多）
	 *     2.手动将新模型的属性全部赋值给旧模型（性能比较差）
	 *     3.使用paramsPrepareParamsStack+preparable拦截器配合使用
	 *       prepare拦截器先执行，先为model赋值 后调用modelDriven的getModel()方法
	 *       将model压入值栈，但是需要在prepare拦截器进行参数传递，defaultstack在
	 *       prepare不能完成参数的传递，因此可以改换成paramsPrepareParamsStack来达到此目的。
	 *       (此方法会带来action的方法增加，不便于维护)
	 *     4.通过设置  modelDriven.refreshModelBeforeResult 为true 即开启 栈顶model的更新的监听
	 *       从而实现将最新的model 放入栈顶中。
	 * @return
	 */
	public String designSurvey(){
		this.model=surveyService.getSurveyAndChildren(sid);
		return "mySurveyDesignPage";
	}
	
	
	/**
	 * 去编辑survey调查
	 */
	public String editSurvey(){
		this.model=surveyService.getSurveyById(sid);
		return "toEditSurveyPage";
	}
	/**
	 * 更新survey调查
	 * @return
	 */
	public String updateSurvey(){
		this.sid=model.getId();
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "toDesignSurveyAction";
	}
	/**
	 * 删除调查 Survey 同时删除 page,question,answer 
	 * @return 我的调查
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "mySurveyAction";
	}
	/**
	 * 清除调查
	 * @return
	 */
	public String clearSurvey(){
		surveyService.clearSurvey(sid);
		return "mySurveyAction";
	}
	/**
	 * 打开/关闭 调查
	 * @return
	 */
	public String toggleStatus(){
		surveyService.toggleStatus(sid);
		return "mySurveyAction";
	}
	/**
	 * 分析调查
	 * @return
	 */
	public String analyzeSurvey(){
		this.model=surveyService.getSurveyAndChildren(sid);
		return "toAnalyzeSurveyPage";
	}
	/**
	 * 到达增加logo页面
	 * @return
	 */
	public String toAddLogoPage(){
		return "toAddLogoPage";
	}
	/**
	 * 文件上传
	 */
	private File logoPhoto;
	private String logoPhotoFileName;
	public String doAddLogo(){
		//如果文件有效
		if(ValidateUtil.isValidate(logoPhotoFileName)){
			//1.处理文件保存
			String dir=getServletContext().getRealPath(Constants.UploadLogoPhoth);
			String ext=logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			//获得系统的纳秒时间
			Long l=System.nanoTime();
			String newFileName=l+ext;
			File newFile=new File(dir, newFileName);
			logoPhoto.renameTo(newFile);
			String relativePath=Constants.UploadLogoPhoth+File.separator+newFileName;
			//2.保存文件路径
			surveyService.updateLogoPhotoPath(sid,relativePath);
		}
		return "toDesignSurveyAction";
	}
	
	/**
	 * 在DoAddLogo 方法之前调用，动态设置 文件上传异常时跳转的页面
	 * @throws Exception
	 */
	public void prepareDoAddLogo() throws Exception {
		dynamiErrorPage="/toAddLogo.jsp";
		super.prepare();
	}
	/**
	 * 判断文件是否存在
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
	 * modelDriven 前调用此方法
	 * @throws Exception
	 */
	/*public void prepareDesignSurvey() throws Exception {
		this.model=surveyService.getSurveyAndChildren(sid);
	}*/
	/**
	 * 注入User
	 */
	public void setUser(User user) {
		this.user=user;
	}
	

}
