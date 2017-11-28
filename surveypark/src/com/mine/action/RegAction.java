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
	 * 注册页面
	 * @return
	 */
	@SkipValidation
	public String toReg(){
		return "regPage";
	}
	/**
	 * 保存用户
	 * @return
	 */
	public String doReg(){
		userService.saveEntity(model);
		return SUCCESS;
	}
	
    /**
     *校验  
     */
	@Override
	public void validate() {
		//1.非空校验
		if(!ValidateUtil.isValidate(model.getEmail())){
			addFieldError("email", "邮箱不能为空！");
		}
		if(!ValidateUtil.isValidate(model.getUsername())){
			addFieldError("username", "用户名不能为空！");
		}
		if(!ValidateUtil.isValidate(model.getNickName())){
			addFieldError("nickname", "称昵不能为空！");
		}
		if(!ValidateUtil.isValidate(model.getPassword())){
			addFieldError("password", "密码不能为空！");
		}
		if(hasErrors()){
			return ;
		}
		//2.密码不一致校验
		if(!model.getPassword().equals(repassword.trim())){
			addFieldError("password", "密码不一致！");
			return;
		}
		//3.邮箱占用校验
		if(userService.isValidatEmail(model.getEmail())){
			addFieldError("email", "该邮箱已注册过！");
		}
		
		super.validate();
	}

}
