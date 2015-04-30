package com.sandbox9.devicedetector.spring.integration;

import com.sandbox9.devicedetector.ReadableUserAgent;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class ReadableUserAgentHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter parameter) {
        return ReadableUserAgent.class.isAssignableFrom(parameter.getParameterType());
    }

    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest request,
                                  WebDataBinderFactory binderFactory) throws Exception {

        return request.getAttribute("readableUserAgent", RequestAttributes.SCOPE_REQUEST);
    }

}