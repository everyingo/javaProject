package com.mine.aop;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;

import com.mine.model.Log;
import com.mine.model.User;
import com.mine.service.LogService;
import com.mine.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

public class Logger {

	@Resource(name="logServiceImpl")
	private LogService logService;
	
	public Object addLog(ProceedingJoinPoint pjp){
		ActionContext ac=ActionContext.getContext();
		Log log=new Log();
		try {
		//设置操作人
		if(ac!=null){
			User u=(User) ac.getSession().get("user");
			if(u!=null){
				log.setOperator("uid:"+u.getId()+" username:"+u.getUsername());
			}
		}
		//操作名称
		String mname=pjp.getSignature().getName();
		log.setOperName(mname);
		//操作参数
		Object[] objs=pjp.getArgs();
		log.setOperParams(StringUtil.arr2str(objs));
		//调用目标对象方法
		Object ret=pjp.proceed();
		//设置操作结果
		log.setOperResult("success");
		//设置结果信息
		if(ret!=null){
			log.setResultMsg(ret.toString());
		}
		return ret;
		} catch (Throwable e) {
			log.setOperResult("failure");
			log.setResultMsg(e.getMessage());
		}finally{
			//保存 日志log
			logService.saveEntity(log);
		}
		return null;
	}
}
