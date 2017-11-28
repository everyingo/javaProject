package com.mine.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.User;
import com.mine.service.UserService;
import com.mine.util.ValidateUtil;

@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User>{

	
	
	private static final long serialVersionUID = -930131403978127626L;
	
	private String repassword;
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	/**
	 * ע��ҳ��
	 * @return
	 */
	@SkipValidation
	public String toReg(){
		return "regPage";
	}
	/**
	 * �����û�
	 * @return
	 */
	public String doReg(){
		userService.saveEntity(model);
		return SUCCESS;
	}
	
    /**
     *У��  
     */
	@Override
	public void validate() {
		//1.�ǿ�У��
		if(!ValidateUtil.isValidate(model.getEmail())){
			addFieldError("email", "���䲻��Ϊ�գ�");
		}
		if(!ValidateUtil.isValidate(model.getUsername())){
			addFieldError("username", "�û�������Ϊ�գ�");
		}
		if(!ValidateUtil.isValidate(model.getNickName())){
			addFieldError("nickname", "���ǲ���Ϊ�գ�");
		}
		if(!ValidateUtil.isValidate(model.getPassword())){
			addFieldError("password", "���벻��Ϊ�գ�");
		}
		if(hasErrors()){
			return ;
		}
		//2.���벻һ��У��
		if(!model.getPassword().equals(repassword.trim())){
			addFieldError("password", "���벻һ�£�");
			return;
		}
		//3.����ռ��У��
		if(userService.isValidatEmail(model.getEmail())){
			addFieldError("email", "��������ע�����");
		}
		
		super.validate();
	}

}
