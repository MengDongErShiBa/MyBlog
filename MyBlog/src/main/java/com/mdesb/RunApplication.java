package com.mdesb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 程序入口在此启动
 * @author 孟冬二十八。
 *
 * 2019年5月22日-下午3:50:29
 */
@MapperScan("com.mdesb.dao")
@SpringBootApplication
public class RunApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RunApplication.class, args);
	}
}
