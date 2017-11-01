//package com.cloud.approve_zuul.filter;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
//import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.util.StringUtils;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//
///**
// * 处理来自post过滤器引起的异常，注意：D版不需要配此过滤器
// * 
// * @author shichangjian
// *
// */
//@Component
//public class ErrorExtFilter extends SendErrorFilter {
//	private static final Log log = LogFactory.getLog(SendErrorFilter.class);
//	protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";
//
//	@Value("${error.path:/error}")
//	private String errorPath;
//
//	@Override
//	public String filterType() {
//		return ERROR_TYPE;
//	}
//
//	@Override
//	public int filterOrder() {
//		return 1101;
//	}
////	@Override
////	public String filterType() {
////		return "error";
////	}
//
////	@Override
////	public int filterOrder() {
////		return 30; // 大于ErrorFilter的值
////	}
//
//	@Override
//	public boolean shouldFilter() {
//		// 判断：仅处理来自post过滤器引起的异常
//	       // 判断：仅处理来自post过滤器引起的异常
//		
////        RequestContext ctx = RequestContext.getCurrentContext();
////        ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");//failed.filter属性来着于DidiFilterProcessor类
////        if(failedFilter != null && failedFilter.filterType().equals("post")) {
////            return true;
////        }
////        return false;
//		
//		RequestContext ctx = RequestContext.getCurrentContext();
//		boolean a = ctx.containsKey("error.status_code");
//		boolean b = !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);
//		return a&&b;
//	}
//	@Override
//	public Object run() {
//		try {
//			RequestContext ctx = RequestContext.getCurrentContext();
//			ZuulException exception = findZuulException(ctx.getThrowable());
//			HttpServletRequest request = ctx.getRequest();
//
//			request.setAttribute("javax.servlet.error.status_code", exception.nStatusCode);
//
//			log.warn("Error during filtering", exception);
//			request.setAttribute("javax.servlet.error.exception", exception);
//
//			if (StringUtils.hasText(exception.errorCause)) {
//				request.setAttribute("javax.servlet.error.message", exception.errorCause);
//			}
//
//			RequestDispatcher dispatcher = request.getRequestDispatcher(
//					this.errorPath);
//			if (dispatcher != null) {
//				ctx.set(SEND_ERROR_FILTER_RAN, true);
//				if (!ctx.getResponse().isCommitted()) {
//					dispatcher.forward(request, ctx.getResponse());
//				}
//			}
//		}
//		catch (Exception ex) {
//			ReflectionUtils.rethrowRuntimeException(ex);
//		}
//		return null;
//	}
//
//	ZuulException findZuulException(Throwable throwable) {
//		if (throwable.getCause() instanceof ZuulRuntimeException) {
//			// this was a failure initiated by one of the local filters
//			return (ZuulException) throwable.getCause().getCause();
//		}
//
//		if (throwable.getCause() instanceof ZuulException) {
//			// wrapped zuul exception
//			return (ZuulException) throwable.getCause();
//		}
//
//		if (throwable instanceof ZuulException) {
//			// exception thrown by zuul lifecycle
//			return (ZuulException) throwable;
//		}
//
//		// fallback, should never get here
//		return new ZuulException(throwable, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
//	}
//
//	public void setErrorPath(String errorPath) {
//		this.errorPath = errorPath;
//	}
//
//}
