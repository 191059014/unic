package com.hb.unic.rbac.config.security;

import com.hb.unic.rbac.config.filter.TokenAuthenticationFilter;
import com.hb.unic.rbac.config.handler.MyAccessDeniedHandler;
import com.hb.unic.rbac.config.handler.MyAuthenticationEntryPoint;
import com.hb.unic.rbac.config.handler.MyLoginFailureHandler;
import com.hb.unic.rbac.config.handler.MyLoginSuccessHandler;
import com.hb.unic.rbac.config.handler.MyLogoutHandler;
import com.hb.unic.rbac.config.handler.MyLogoutSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import javax.annotation.Resource;

/**
 * SpringSecurity配置
 *
 * @author Mr.Huang
 * @version v0.1, 2021/9/15 14:40, create by huangbiao.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * 用户授权
     */
    @Resource
    private UserDetailsService myUserDetailsService;

    /**
     * 配置
     */
    @Resource
    private MySercurityConfig mySercurityConfig;

    /**
     * 自定义认证管理
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 防止当用户不存在时，框架抛出来的是BadCredentialsException
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 禁用csrf
            .cors().and().csrf().disable()
            // 基于token，所以不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // 打开认证配置
            .and().authorizeRequests()
            // 其他所有请求需要身份认证
            .anyRequest().authenticated();

        http
            // 指定表单登陆方式
            .formLogin()
            // 指定登录处理url
            .loginProcessingUrl(mySercurityConfig.getLoginUrl())
            // 定义登录时的用户名字段
            .usernameParameter("username")
            // 定义登录时的密码字段
            .passwordParameter("password")
            // 登陆成功处理器
            .successHandler(new MyLoginSuccessHandler())
            // 登陆失败处理器
            .failureHandler(new MyLoginFailureHandler())
            // 登录接口直接跳过权限
            .permitAll();

        http
            // 开启注销设置
            .logout()
            // 指定注销处理url
            .logoutUrl(mySercurityConfig.getLogoutUrl())
            // 注销处理器
            .addLogoutHandler(new MyLogoutHandler())
            // 注销成功处理器
            .logoutSuccessHandler(new MyLogoutSuccessHandler())
            // 注销接口直接跳过权限
            .permitAll();

        http
            // 开启异常处理
            .exceptionHandling()
            // 认证过的用户访问无权限资源时的异常处理器
            .accessDeniedHandler(new MyAccessDeniedHandler())
            // 匿名用户访问无权限资源时的异常处理器
            .authenticationEntryPoint(new MyAuthenticationEntryPoint());

        // 前后端分离，使用token机制，先进行token认证
        // 将顺序放在注销过滤器前，保证注销的url也经过此过滤器
        // 过滤器链的顺序见org.springframework.security.config.annotation.web.builders.FilterComparator
        // 这里直接new对象，如果用注入的话，下面配置的忽略请求会失效，依然会经过此过滤器
        http.addFilterBefore(new TokenAuthenticationFilter(mySercurityConfig), LogoutFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略请求，不走过滤器链
        WebSecurity ws = web.ignoring().and();
        log.info("IgnoreUrls: {}", mySercurityConfig.getIgnoreUrls());
        for (String ignoreUrl : mySercurityConfig.getIgnoreUrls()) {
            ws.ignoring().antMatchers(ignoreUrl);
        }
    }
}
