package com.sandbox9.devicedetector.spring.integration;

import com.sandbox9.devicedetector.ReadableUserAgent;
import com.sandbox9.devicedetector.UserAgentParser;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadableUserAgentResolverHandlerInterceptor extends HandlerInterceptorAdapter {

    private UserAgentParser userAgentParser;

    public ReadableUserAgentResolverHandlerInterceptor(){
        userAgentParser = new UserAgentParser();
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ReadableUserAgent readableUserAgent = userAgentParser.parse(request.getHeader("User-Agent"));
        request.setAttribute("readableUserAgent", readableUserAgent);

        return true;
    }

}