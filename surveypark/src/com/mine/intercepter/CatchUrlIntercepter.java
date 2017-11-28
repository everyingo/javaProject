package com.mine.intercepter;




import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mine.service.RightService;
import com.mine.util.ValidateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 权限url地址 捕获拦截器
 * @author Administrator
 *
 */
public class CatchUrlIntercepter implements Interceptor{

	
	private static final long serialVersionUID = -8168382387505486089L;

	public void destroy() {
	}

	public void init() {
		
	}
	public String intercept(ActionInvocation arg0) throws Exception {
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
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		RightService rs=ac.getBean(RightService.class);
		rs.createRightByUrl(url);
		return arg0.invoke();
	}

	

}
