package cn.xinhang.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sun.rmi.runtime.Log;

import java.util.function.LongToIntFunction;

/**
 * 这是SpringMVC的配置类，主要用来添加拦截器
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")          //拦截所有请求
                .excludePathPatterns("/lr/**","/fastdfs/**");//放行/lr和/fastdfs下面的所有资源请求
    }
}
