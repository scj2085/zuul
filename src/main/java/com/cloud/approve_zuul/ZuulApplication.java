package com.cloud.approve_zuul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

import com.cloud.approve_zuul.config.FilterConfiguration;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;


/**
 * Hello world!
 *
 */
@EnableConfigurationProperties({FilterConfiguration.class})//动态加载过虑器配置类，目前还不成熟 ，只实现简单功能，无法直接注入API网关服务的spring容器中加载的实例来使用，无法直接通过注入RestTemplate等实例，在动态过滤器中对各个微服务 发起请求。
@EnableZuulProxy//开启zuul的API网关服务功能
@SpringCloudApplication//它整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
public class ZuulApplication {
	
    public static void main( String[] args ){
    	new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }
    /**
     * 创建动态加载过滤器的实例
     * @param filterConfiguration
     * @return
     */
    @Bean
    public FilterLoader filterLloader(FilterConfiguration filterConfiguration){
    	FilterLoader filterLoader = FilterLoader.getInstance();
    	filterLoader.setCompiler(new GroovyCompiler());
    	try {
			FilterFileManager.setFilenameFilter(new GroovyFileFilter());
			//API网关应用会每隔5秒，从API网关所在位置的filter/pre和filter/post目录下获取定义的Groovy定义的过滤器，并进行编译和动态加载，zuul.filter.interval:动态加载时间间隔，zuul.filter.roo:调整根目录的位置，这里写死了/pre和/post目录，可做进一步扩展
			FilterFileManager.init(
					filterConfiguration.getInterval(),
					filterConfiguration.getRoot() + "/pre",
					filterConfiguration.getRoot() + "/post");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    	return filterLoader;
    }
    /**
     * 
     * @return
     */
    @Bean
    @RefreshScope//此注解来将zuul的配置内容动态化
    @ConfigurationProperties("zuul")//"zuul"是git仓库approve-zuul.yml中的属性key，API网关服务的规则已经提取到git仓库，文件名必须和spring:application:name: approve-zuul		的名称一样
    public ZuulProperties zuulProperties(){
    	return new ZuulProperties();
    }
    
}
