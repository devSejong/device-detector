package com.sandbox9.devicedetector.spring.integration;

import com.sandbox9.devicedetector.dto.ReadableUserAgent;
import com.sandbox9.devicedetector.UserAgentParser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring@MVC에서 파라미터로 UserAgent를 받을 수 있도록 만들어 주는 argument resolver
 * @author devSejong
 * @since 1.0
 */
public class ReadableUserAgentHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private UserAgentParser parser;

    public ReadableUserAgentHandlerMethodArgumentResolver(){
        parser = new UserAgentParser();
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return ReadableUserAgent.class.isAssignableFrom(parameter.getParameterType());
    }

    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest request,
                                  WebDataBinderFactory binderFactory) throws Exception {

        return parser.parse(request.getNativeRequest(HttpServletRequest.class));
    }

}