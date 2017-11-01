package com.cloud.approve_zuul;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.cloud.approve_zuul.filter.ErrorFilter;
//import com.cloud.approve_zuul.filter.AccessFilter;
import com.cloud.approve_zuul.filter.ThrowExceptionFilter;

/**
 * Hello world!
 *
 */
@EnableZuulProxy
@SpringCloudApplication//它整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
public class Application {
    public static void main( String[] args ){
    	new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
	/**
	 * 自定义网关zuul过滤器，实例化该过滤器才能生效
	 * @return
	 */
//	@Bean
//	public AccessFilter accessFilter() {
//		return new AccessFilter();
//	}
    @Bean
    public ThrowExceptionFilter throwExceptionFilter(){
    	return new ThrowExceptionFilter();
    }
    @Bean
    public ErrorFilter errorFilter(){
    	return new ErrorFilter();
    }
}
