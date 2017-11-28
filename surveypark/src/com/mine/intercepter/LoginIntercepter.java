package com.mine.intercepter;



import com.mine.action.BaseAction;
import com.mine.action.LoginAction;
import com.mine.action.RegAction;
import com.mine.aware.UserAware;
import com.mine.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * ��¼������
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class LoginIntercepter implements Interceptor{

	
	private static final long serialVersionUID = -8168382387505486089L;

	public void destroy() {
	}

	public void init() {
	}

	
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction action=(BaseAction) arg0.getAction();
		/**���action ��LoginAction����¼�� �� RegAction(ע��) ��ֱ�ӷŹ���*/
		if(action instanceof LoginAction || action instanceof RegAction){
			/**�Ź�*/
			return arg0.invoke();
		}else{
			User user=(User) arg0.getInvocationContext().getSession().get("user");
			if(user==null){
				/**���ز�ת����¼ҳ��*/
				return "login";
			}else{
				/**�Ź�*/
				if(action instanceof UserAware){
					/**ע��User����*/
					((UserAware)action).setUser(user);
				}
				return arg0.invoke();
			}
		}
	}

	

}
