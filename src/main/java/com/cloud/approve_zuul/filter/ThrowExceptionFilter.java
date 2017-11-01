package com.cloud.approve_zuul.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 测试过滤器中抛出异常
 * 
 * @author shichangjian
 *
 */
public class ThrowExceptionFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);

	@Override
	public Object run() {
		
	       log.info("This is a pre filter, it will throw a RuntimeException");
	        doSomething();
	        return null;
		
		
//	    log.info("This is a pre filter, it will throw a RuntimeException");
//	    RequestContext ctx = RequestContext.getCurrentContext();
//	    try {
//	        doSomething();
//	    } catch (Exception e) {
////	    	status：对应error.status_code参数的值
////	    	exception：对应error.exception参数中Exception的类型
////	    	message：对应error.exception参数中Exception的message信息。对于message的信息，我们在过滤器中还可以通过ctx.set("error.message", "自定义异常消息");来定义更友好的错误信息。SendErrorFilter会优先取error.message来作为返回的message内容，如果没有的话才会使用Exception中的message信息
//	        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	        ctx.set("error.message", "自定义异常消息");
//	        ctx.set("error.exception", e);
//	    }
//	  	return null;
	}

	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public int filterOrder() {

		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	private void doSomething() {
		throw new RuntimeException("****************Exist some errors...");
	}
}
