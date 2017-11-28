package com.mine.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Log;
import com.mine.service.LogService;

@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log>{

	
	private static final long serialVersionUID = -6304494948095312755L;

	@Resource(name="logServiceImpl")
	private LogService logService;
	
	private List<Log> logs;
	
	private Integer n=2;
	
	public Integer getN() {
		return n;
	}
	public void setN(Integer n) {
		this.n = n;
	}
	public List<Log> getLogs() {
		return logs;
	}
	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}


	/**
	 * ��ѯ���е���־
	 * @return
	 */
	public String findAllLogList(){
		 this.logs=logService.findLogList();
		 return "NearLogListPage";
	}
	/**
	 * ��־�б�(�ֱ��ѯ)
	 * @return
	 */
	public String findNearLogList(){
		this.logs=logService.findNearLogList(n);
		return "NearLogListPage";
	}
}
