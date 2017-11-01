//package com.cloud.approve_zuul.filter;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//
///**
// * 统一异常处理，处理pre,route,post中的异常，此过滤器处理后，进入post处理返回结果，之后如果在post中抛出异常是不会再进此ErrorFilter过滤器，解决方法是在定义一个ErrorExFilter过滤器。注意：D版不需要配此过滤器
// * @author shichangjian
// *
// */
//@Component
//public class ErrorFilter extends ZuulFilter {
//
//	Logger log = LoggerFactory.getLogger(ErrorFilter.class);
//
//	@Override
//	public Object run() {
//		RequestContext ctx = RequestContext.getCurrentContext();
//		Throwable throwable = ctx.getThrowable();
//		log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
//		ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//		ctx.set("error.exception", throwable.getCause());
//		ctx.set("error.message", "自定义异常消息");
//        ctx.set("error.data", throwable.getCause().getMessage());
//		return null;
//	}
//
//	@Override
//	public boolean shouldFilter() {
//
//		return true;
//	}
//
//	@Override
//	public int filterOrder() {
//
//		return 10;
//	}
//
//	@Override
//	public String filterType() {
//
//		return "error";
//	}
//
//}
