//package com.cloud.approve_zuul.filter;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//
///***
// * 在实现了自定义过滤器之后，还需要实例化该过滤器才能生效，我们只需要在应用主类中增加bin
// * 
// * @bean http:// localhost:5555/api-a/add?a=1&b=2：返回401错误 http://
// *       localhost:5555/api-a/add?a=1&b=2&accessToken=token：正确路由到server-A，
// *       并返回计算内容
// * @author shichangjian
// **/
//@Component
//public class AccessFilter extends ZuulFilter {
//	private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
//
//	//验证url后加“accessToken”
//	@Override
//	public Object run() {// 根据下面四个方法来控制此run（）方法，filterType()，return "pre"
//		// 表示在请求路由之前调用
//		// run：过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
//		 RequestContext ctx = RequestContext.getCurrentContext();
//		 HttpServletRequest request = ctx.getRequest();
//		 log.info(String.format("%s request to %s", request.getMethod(),
//		 request.getRequestURL().toString()));
//		 Object accessToken = request.getParameter("accessToken");
//		 if(accessToken == null) {
//			 log.warn("access token is empty");
//			 ctx.setSendZuulResponse(false);//false是不对其进行路由
////			 ctx.setResponseStatusCode(401);
//			 ctx.setResponseBody("access token is empty");
//			 return null;
//		 }
//		 
//		log.info("access token ok");
//		return null;
//
//	}
//	@Override
//	public boolean shouldFilter() {
//		// shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。我们直接返回true，所以该过滤器总是生效
//		return true;
//	}
//
//	@Override
//	public int filterOrder() {
//		// filterOrder：通过int值来定义过滤器的执行顺序
//		return 0;
//	}
//
//	@Override
//	public String filterType() {
//		// filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
//		// pre：可以在请求被路由之前调用，此过滤器执行顺序-3，是最先执行的过滤器，主要用来判断当前请求是通过DispatcherServlet还是通过zuulServlet来处理运行的，除了通过/zuul/*路径访问的请求会绕过DispatcherServlet,被zuulServlet处理，主要处理大文件上传，
//		// routing：在路由请求时候被调用
//		// post：在routing和error过滤器之后被调用
//		// error：处理请求时发生错误时被调用
//		return "pre";
//	}
//
//}
