package com.mine.action;

import javax.annotation.Resource;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.User;
import com.mine.service.UserService;
import com.mine.util.MD5Util;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> {

	
	private static final long serialVersionUID = 3984080878278039947L;
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	/**
	 * 去登录界面
	 * @return
	 */
	public String toLoginPage(){
		return "loginPage";
	}
	/**
	 * 登录成功
	 * @return
	 */
	public String doLogin(){
		return SUCCESS;
	}
	/**
	 * validate 加后缀 表示只对 doLogin方法进行验证
	 */
	public void validateDoLogin() {
		User user=userService.validateLoginInfo(model.getUsername(),MD5Util.string2MD5(model.getPassword()));
		if(user==null){
			addActionError("用户名或密码不正确！");
		}else{
			/**计算用户权限总和*/
			user.calculateRightSum();
			/**put session*/
			getSessionMap().put("user", user);
		}
		super.validate();
	}
	
	

}
