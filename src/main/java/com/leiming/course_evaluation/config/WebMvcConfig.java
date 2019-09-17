package com.leiming.course_evaluation.config;

import com.leiming.course_evaluation.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginFilter loginFilter;
    //设置项目资源路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
    //设置拦截器放行url
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginFilter).addPathPatterns("/**")
                .excludePathPatterns("/","/api/**","/css/**","/images/**","/js/**","/lib/**","/getGifCode","/login","/logout");
    }

    //扩大session作用范围
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
        return new OpenEntityManagerInViewFilter();
    }
}
