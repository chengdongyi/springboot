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
@EnableGlobalMethodSecurity(securedEnabled = true) // 开启方法级的动态授权
public class Securityconfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问,功能页只有对应有权限的人才能访问
        //请求授权的规则  hasAnyRole() e
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1") // hasAnyRole() 任何角色都可以访问
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认会到登录页,需要开启登录的页面
          /*
            没有授权会自动跳转到登录页面        http://localhost:8080/login
               定制登录页.loginPage("/toLogin")  http://localhost:8080/toLogin
               最后，走回   http://localhost:8080/login(因为要认证用户)，页面的action动作为th:action="@{/login}"
               其对应      http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login")
         */
//        http.formLogin().loginPage("/toLogin").usernameParameter("name").passwordParameter("pwd").loginProcessingUrl("/login");

//        http.formLogin().loginPage("login.jsp")//登录页面
//                .loginProcessingUrl("/login")// Security 提供的
//                .successForwardUrl("/index.jsp")// 成功后指定的页面
//                .failureForwardUrl("/fail.jsp") // 失败页面
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("login.jsp")
//                .invalidateHttpSession(true)
//                .permitAll(); // 清空session




//        .usernameParameter("name").passwordParameter("pwd")


//        http.formLogin();
        http.formLogin().loginPage("/toLogin");
        //注销 开启了注销功能，跳到首页

        //防止网站工具 ：get、post
        http.csrf().disable();//关闭csrf功能
        http.logout().logoutSuccessUrl("/");
//
//        //开启记住我功能 cookie 默认保存两周 自定义接收前端的参数
        http.rememberMe().rememberMeParameter("remember-me");
    }
    //认证,springboot 2.1.X 可以直接使用
    //密码编码：PasswordEncoder
    //在spring Security 5.0+ 新增了很多的加密方法
    @Override
    protected  void configure(AuthenticationManagerBuilder auth)throws  Exception{
        //这些数据正常应该从数据库中读
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
//                .and()
//                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
//                .and()
//                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
