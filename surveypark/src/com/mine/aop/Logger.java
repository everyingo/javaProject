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
		//���ò�����
		if(ac!=null){
			User u=(User) ac.getSession().get("user");
			if(u!=null){
				log.setOperator("uid:"+u.getId()+" username:"+u.getUsername());
			}
		}
		//��������
		String mname=pjp.getSignature().getName();
		log.setOperName(mname);
		//��������
		Object[] objs=pjp.getArgs();
		log.setOperParams(StringUtil.arr2str(objs));
		//����Ŀ����󷽷�
		Object ret=pjp.proceed();
		//���ò������
		log.setOperResult("success");
		//���ý����Ϣ
		if(ret!=null){
			log.setResultMsg(ret.toString());
		}
		return ret;
		} catch (Throwable e) {
			log.setOperResult("failure");
			log.setResultMsg(e.getMessage());
		}finally{
			//���� ��־log
			logService.saveEntity(log);
		}
		return null;
	}
}
