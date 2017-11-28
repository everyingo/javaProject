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
	 * ȥ��¼����
	 * @return
	 */
	public String toLoginPage(){
		return "loginPage";
	}
	/**
	 * ��¼�ɹ�
	 * @return
	 */
	public String doLogin(){
		return SUCCESS;
	}
	/**
	 * validate �Ӻ�׺ ��ʾֻ�� doLogin����������֤
	 */
	public void validateDoLogin() {
		User user=userService.validateLoginInfo(model.getUsername(),MD5Util.string2MD5(model.getPassword()));
		if(user==null){
			addActionError("�û��������벻��ȷ��");
		}else{
			/**�����û�Ȩ���ܺ�*/
			user.calculateRightSum();
			/**put session*/
			getSessionMap().put("user", user);
		}
		super.validate();
	}
	
	

}
