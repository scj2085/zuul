//package com.cloud.approve_zuul.filter;
//
//import com.netflix.zuul.FilterProcessor;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//
///**
// * FilterProcessor是zuul过滤器的核心处理器，处理从ErrorFilter，进入post中抛出的异常，做个标记，以便在ErrorExFilter中分辨出post异常。注意：D版不需要配此过滤器
// * @author shichangjian
// *
// */
//public class DidiFilterProcessor extends FilterProcessor {
//
//	
//    /* 
//     * 扩展processZuulFilter(ZuulFilter filter)方法，当过滤器执行抛出异常的时候，我们捕获它，并往请求上下中记录一些信息
//     * @see com.netflix.zuul.FilterProcessor#processZuulFilter(com.netflix.zuul.ZuulFilter)
//     */
//    @Override
//    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
//        try {
//            return super.processZuulFilter(filter);
//        } catch (ZuulException e) {
//            RequestContext ctx = RequestContext.getCurrentContext();
//            ctx.set("failed.filter", filter);//我们创建了一个FilterProcessor的子类，并重写了processZuulFilter(ZuulFilter filter)，虽然主逻辑依然使用了父类的实现，但是在最外层，我们为其增加了异常捕获，并在异常处理中为请求上下文添加了failed.filter属性，以存储抛出异常的过滤器实例。在实现了这个扩展之后，我们也就可以完善之前ErrorExtFilter中的shouldFilter()方法，通过从请求上下文中获取该信息作出正确的判断
//            throw e;
//        }
//    }
//    
//    
////    该类中定义了下面过滤器调用和处理相关的核心方法：
//
////    getInstance()：该方法用来获取当前处理器的实例
////    setProcessor(FilterProcessor processor)：该方法用来设置处理器实例，可以使用此方法来设置自定义的处理器
////    processZuulFilter(ZuulFilter filter)：该方法定义了用来执行filter的具体逻辑，包括对请求上下文的设置，判断是否应该执行，执行时一些异常处理等
////    getFiltersByType(String filterType)：该方法用来根据传入的filterType获取API网关中对应类型的过滤器，并根据这些过滤器的filterOrder从小到大排序，组织成一个列表返回
////    runFilters(String sType)：该方法会根据传入的filterType来调用getFiltersByType(String filterType)获取排序后的过滤器列表，然后轮询这些过滤器，并调用processZuulFilter(ZuulFilter filter)来依次执行它们
////    preRoute()：调用runFilters("pre")来执行所有pre类型的过滤器
////    route()：调用runFilters("route")来执行所有route类型的过滤器
////    postRoute()：调用runFilters("post")来执行所有post类型的过滤器
////    error()：调用runFilters("error")来执行所有error类型的过滤器
//    
//}
