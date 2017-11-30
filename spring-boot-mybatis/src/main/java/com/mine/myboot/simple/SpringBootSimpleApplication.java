package com.mine.myboot.simple;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.mine.myboot.simple.common.properties.UserProperties;

@SpringBootApplication
@EnableConfigurationProperties({UserProperties.class})
@MapperScan("com.mine.myboot.simple.dao")
public class SpringBootSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSimpleApplication.class, args);
	}
}
