package com.example.config;

import com.example.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Securityconfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    /**
     * 功能描述: 授权
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         hasAnyRole(): 任何角色都可以访问

         http.formLogin().loginPage("login.jsp")      登录页面
                  .loginProcessingUrl("/login")       Security 提供的
                  .successForwardUrl("/index.jsp")    成功后指定的页面
                  .failureForwardUrl("/fail.jsp")     失败页面
                  .permitAll()
                  .and()
                  .logout()
                  .logoutUrl("/logout")
                  .logoutSuccessUrl("login.jsp")
                  .invalidateHttpSession(true)
                  .permitAll(); // 清空session

                  .usernameParameter("name") 默认 username
                  .passwordParameter("pwd")  默认 password
         */
        http.authorizeRequests()
                .antMatchers("/", "/tourist/**").permitAll();
//                .antMatchers("/user/**").hasRole("user")
//                .antMatchers("/vip/**").hasRole("vip")
//                .antMatchers("/svip/**").hasRole("svip");
        /*
            注解方式：
                1. 在配置类上添加 @EnableGlobalMethodSecurity(securedEnabled = true) 注解
                2. 在请求方法上添加 @Secured("user") 注解并指定角色
         */


        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/user/login")
                .successForwardUrl("/index").permitAll();

        //防止网站工具 ：get、post
        http.csrf().disable();//关闭csrf功能

        //注销 开启了注销功能，跳到首页
        http.logout().logoutSuccessUrl("/");

        //开启记住我功能 cookie 默认保存两周 自定义接收前端的参数
        http.rememberMe().rememberMeParameter("remember-me");

    }

    /**
     * 功能描述: 授权
     */
    @Override
    protected  void configure(AuthenticationManagerBuilder auth)throws  Exception{

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

        // 内存中加载用户信息
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("user")
//                .and()
//                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("user","vip","svip");

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
