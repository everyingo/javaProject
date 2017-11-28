package com.mine.listener;




import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mine.service.LogService;
import com.mine.util.LogUtil;




@Component
@SuppressWarnings("rawtypes")
public class LogListener implements ApplicationListener {

	@Resource(name="logServiceImpl")
	private LogService logService;
	
	public void onApplicationEvent(ApplicationEvent arg0) {
		String tableName=LogUtil.createLogTableName(0);
		logService.createLogTable(tableName);
		
		String tableName1=LogUtil.createLogTableName(1);
		logService.createLogTable(tableName1);
		
		String tableName2=LogUtil.createLogTableName(2);
		logService.createLogTable(tableName2);
		
		System.out.println("-------------------日志表初始化完成！！---------------------");
	}

}
