package com.mine.core.task.product;

import org.springframework.scheduling.annotation.Scheduled;

//@Component
public class ProductTask {

	@Scheduled(cron = "0/5 * *  * * ? ")
	public void task() {
		System.out.println("qw");
	}
}
