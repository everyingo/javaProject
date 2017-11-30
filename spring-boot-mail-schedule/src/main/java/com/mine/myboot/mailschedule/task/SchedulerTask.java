package com.mine.myboot.mailschedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

	private int count = 0;

	@Scheduled(cron = "*/5 * * * * ?")
	public void taskMethod() {
		System.out.println("count:" + (count++));
	}
}
