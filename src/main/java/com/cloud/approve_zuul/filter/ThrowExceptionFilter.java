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
// * 测试过滤器中抛出异常,通过测试，
// * c版的自定义异常：
// * 1.是通过在RequestContext中放入状态码，然后有errorFilter拦截，才能抛出异常，
// * 2.pre，route，post这三个阶段抛出的异常可以被error过滤器处理，然后再被post过滤器处理，直接返回页面，实现返回处理结果，但是被post处理时如果抛出异常，后面就没有过滤器接手处理了，就不会输出异常信息，处理办法是在定义一个ErrorExFilter过滤器，这个过滤器不需要处理所有出现的异常，仅对post抛出的异常有效，就是改变执行顺序，在ErrorFilter之后执行，还要扩展FilterProcessor在异常处理请求上下文中添加failed.filter属性，存储抛出异常过滤器实例，并在ErrorExFilter中的shouldFilter方法中获取此属性，如不为null就是post过滤器引起的 异常
// * D版处理了上面2条缺陷，在D版省略1,2
// * 
// * 
// * @author shichangjian
// *
// */
//@Component
//public class ThrowExceptionFilter extends ZuulFilter {
//
//	private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);
//
//	@Override
//	public Object run() {
//		
//	       log.info("This is a pre filter, it will throw a RuntimeException");
////	       doSomething();
////	       return null;
//		
//		
////	    log.info("This is a pre filter, it will throw a RuntimeException");
////	    RequestContext ctx = RequestContext.getCurrentContext();
////	    doSomething();
////	    try {
////	        doSomething();
////	    } catch (Exception e) {
////	    	status：对应error.status_code参数的值
////	    	exception：对应error.exception参数中Exception的类型
////	    	message：对应error.exception参数中Exception的message信息。对于message的信息，我们在过滤器中还可以通过ctx.set("error.message", "自定义异常消息");来定义更友好的错误信息。SendErrorFilter会优先取error.message来作为返回的message内容，如果没有的话才会使用Exception中的message信息
////	    	ctx.setSendZuulResponse(false);//false是不对其进行路由
////	    	ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
////	        ctx.set("error.message", "自定义异常消息");
////	        ctx.set("error.exception", e);
////	        throw new RuntimeException("****************Exist some errors...");
////	    }
//	  	return null;
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
//		return 0;
//	}
//
//	@Override
//	public String filterType() {
////		return "pre";
//		return "post";
//	}
//
//	private void doSomething() {
//		throw new RuntimeException("****************Exist some errors...");
//	}
//}
