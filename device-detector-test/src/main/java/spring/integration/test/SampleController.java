package spring.integration.test;

import com.sandbox9.devicedetector.ReadableUserAgent;
import com.sandbox9.devicedetector.spring.integration.ReadableUserAgentHandlerMethodArgumentResolver;
import com.sandbox9.devicedetector.spring.integration.ReadableUserAgentResolverHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@Controller
public class SampleController extends WebMvcConfigurerAdapter {

    @RequestMapping("/")
    @ResponseBody
    ReadableUserAgent home(ReadableUserAgent ua) {
        return ua;
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReadableUserAgentResolverHandlerInterceptor());
    }

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ReadableUserAgentHandlerMethodArgumentResolver());
    }
}