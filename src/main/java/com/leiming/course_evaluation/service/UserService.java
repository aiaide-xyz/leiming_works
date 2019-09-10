package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.User;
import com.leiming.course_evaluation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
//用spring security默认登录方式
//继承UserDetailsService接口
public class UserService implements UserDetailsService {
    //spring security默认处理登录的的类
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = userRepository.findByUsername(username);
        //用户名,密码跟权限,密码必须要设置加密方式
        //User实现UserDetails接口
        //username,passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
