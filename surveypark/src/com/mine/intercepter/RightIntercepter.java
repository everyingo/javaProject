package com.mine.intercepter;




import com.mine.action.BaseAction;
import com.mine.aware.UserAware;
import com.mine.model.Right;
import com.mine.model.User;
import com.mine.util.PowerUtil;
import com.mine.util.ValidateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 登录 权限 拦截器
 * @author Administrator
 *
 */
public class RightIntercepter implements Interceptor{

	
	private static final long serialVersionUID = -8168382387505486089L;

	public void destroy() {
	}

	public void init() {
	}

	
	
	public String intercept(ActionInvocation arg0) throws Exception {
		
		BaseAction<?> baseAction=(BaseAction<?>) arg0.getAction();
		
		ActionProxy proxy= arg0.getProxy();
		String ns=proxy.getNamespace();
		String actionName=proxy.getActionName();
		if(!ValidateUtil.isValidate(ns) || ns.equals("/")){
			ns="";
		}
		if(actionName.contains("?")){
			actionName=actionName.substring(0,actionName.indexOf("?"));
		}
		String url=ns+"/"+actionName;
		Right right=PowerUtil.rightMap.get(url);
		if(right==null || right.getCommon()==null?false:right.getCommon()){
			/**放行*/
			return arg0.invoke();
		}else{
			User user=(User) arg0.getInvocationContext().getSession().get("user");
			/**是否登录*/
			if(user!=null){
				/**注入User*/
				if(baseAction instanceof UserAware){
					UserAware ua=(UserAware) baseAction;
					ua.setUser(user);
				}
				/**是否拥有超级管理员权限*/
				if(user.isSuperAdmin()){
					/**放行*/
					return arg0.invoke();
				}else{
					/**是否有权限*/
					if(user.hasRight(right)){
						/**放行*/
						return arg0.invoke();
					}else{
						return "error_no_right";
					}
				}
			}else{
				/**拦截并转至登录页面*/
				return "login";
			}
		}
	}

	

}
