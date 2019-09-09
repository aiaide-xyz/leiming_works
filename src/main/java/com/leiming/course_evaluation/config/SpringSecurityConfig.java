package com.leiming.course_evaluation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Web应用安全适配器
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    //设定加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public void configure(HttpSecurity httpSecurity) throws Exception {
        //表单登录验证
        httpSecurity.formLogin()
                .and()
                //所有表单验证都要验证
                .authorizeRequests().anyRequest()
                .authenticated();
    }
}
