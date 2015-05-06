package spring.integration.test;

import com.sandbox9.devicedetector.spring.integration.ReadableUserAgentHandlerMethodArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;


@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ReadableUserAgentHandlerMethodArgumentResolver());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
