package com.cloud.approve_zuul.filter.post;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cloud.approve_zuul.filter.pre.PreFilter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
@Component
public class PostFilter extends ZuulFilter{
	
	Logger log = LoggerFactory.getLogger(PreFilter.class);
	
	@Override
	public Object run() {
		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		try {
			response.getOutputStream().print("I am ********");
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 2000;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}
}
