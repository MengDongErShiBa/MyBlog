package com.mdesb.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
/**
 * druid数据源配置
 * @author 孟冬二十八。
 *
 * 2019年5月22日-下午4:20:47
 */
@Configuration
public class DruidConfiguration {

	/**
	 * 注册druid为bean
	 * @return
	 */
	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	public 	DataSource druid(){
		return new DruidDataSource();
	}
}
