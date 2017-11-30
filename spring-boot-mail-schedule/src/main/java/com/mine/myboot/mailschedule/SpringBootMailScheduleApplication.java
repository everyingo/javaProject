package com.mine.myboot.mailschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootMailScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMailScheduleApplication.class, args);
	}
}
