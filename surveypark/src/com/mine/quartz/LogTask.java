package com.mine.quartz;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mine.service.LogService;
import com.mine.util.LogUtil;

@Component
public class LogTask {

	@Resource(name="logServiceImpl")
	private LogService logService;
	
	/**
	 * 每个月15号00:15分生成日志表
	 */
	@Scheduled(cron="0 15 0 15 * ?")
	public void task(){
		String tableName=LogUtil.createLogTableName(1);
		logService.createLogTable(tableName);
		
		String tableName1=LogUtil.createLogTableName(2);
		logService.createLogTable(tableName1);
		
		String tableName2=LogUtil.createLogTableName(3);
		logService.createLogTable(tableName2);
		
		System.out.println("---------timer-----------");
	}
	
}
