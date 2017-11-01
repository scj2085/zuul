package com.cloud.approve_zuul.exception;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class DidiErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
		Map<String, Object> result = super.getErrorAttributes(requestAttributes, includeStackTrace);
		RequestContext ctx = RequestContext.getCurrentContext();
		if(StringUtils.isEmpty(ctx.getThrowable())) return null;
		ZuulException exception = findZuulException(ctx.getThrowable());
		System.err.println(exception.getCause().getMessage());
		// result.remove("exception");
		result.get(requestAttributes);
		result.put("timestamp",new Date());
		result.put("status",600);
		result.put("error","错误！");
		result.put("exception","ServiceException");
		result.put("message","pre:异常");
		for (Map.Entry<String, Object> entry : result.entrySet()) {
			System.err.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
		return result;
	}
	ZuulException findZuulException(Throwable throwable) {
		if (throwable.getCause() instanceof ZuulRuntimeException) {
			// this was a failure initiated by one of the local filters
			return (ZuulException) throwable.getCause().getCause();
		}

		if (throwable.getCause() instanceof ZuulException) {
			// wrapped zuul exception
			return (ZuulException) throwable.getCause();
		}

		if (throwable instanceof ZuulException) {
			// exception thrown by zuul lifecycle
			return (ZuulException) throwable;
		}

		// fallback, should never get here
		return new ZuulException(throwable, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
	}
}
