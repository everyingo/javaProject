package com.mine.intercepter;



import com.mine.action.BaseAction;
import com.mine.action.LoginAction;
import com.mine.action.RegAction;
import com.mine.aware.UserAware;
import com.mine.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 登录拦截器
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
		/**如果action 是LoginAction（登录） 或 RegAction(注册) 则直接放过。*/
		if(action instanceof LoginAction || action instanceof RegAction){
			/**放过*/
			return arg0.invoke();
		}else{
			User user=(User) arg0.getInvocationContext().getSession().get("user");
			if(user==null){
				/**拦截并转至登录页面*/
				return "login";
			}else{
				/**放过*/
				if(action instanceof UserAware){
					/**注入User对象*/
					((UserAware)action).setUser(user);
				}
				return arg0.invoke();
			}
		}
	}

	

}
