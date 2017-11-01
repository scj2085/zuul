package com.cloud.approve_zuul.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 动态加载过滤器配置类
 * @author shichangjian
 *
 */
@ConfigurationProperties("zuul.filter")
public class FilterConfiguration {

	private String root;
	private Integer interval;
	
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	
}
