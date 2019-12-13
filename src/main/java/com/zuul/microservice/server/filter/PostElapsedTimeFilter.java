package com.zuul.microservice.server.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostElapsedTimeFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PostElapsedTimeFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("Coming in post filter");

        Long startTime = (Long) request.getAttribute("startTime");
        Long finalTime = System.currentTimeMillis();
        Long  elapsedTime = finalTime - startTime;

        log.info(String.format("Elapsed Time in seconds %s seg.",elapsedTime.doubleValue()/100.00));
        log.info(String.format("Elapsed Time in milliseconds %s ms.",elapsedTime));
        return null;
    }
}
