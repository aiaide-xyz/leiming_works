package com.leiming.course_evaluation.config;

import com.leiming.course_evaluation.handler.LoginFailedHandler;
import com.leiming.course_evaluation.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Web应用安全适配器
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginFailedHandler loginFailedHandler;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    //设定加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public void configure(HttpSecurity httpSecurity) throws Exception {
        //表单登录验证
        httpSecurity.formLogin()
                .loginPage("/login")
                .failureHandler(loginFailedHandler)
                .successHandler(loginSuccessHandler)
                .loginProcessingUrl("/loginAction")
                .and()
                //所有表单验证都要验证
                .authorizeRequests()
                //设置放行的资源
                .antMatchers("/login","/code/image","/css/**","/fonts/**","/img/**","/js/**","/toRegister").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //解除跨域安全限制
                .csrf().disable();
    }
}
