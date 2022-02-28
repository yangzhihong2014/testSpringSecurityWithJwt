package com.example.testSpringSecurityWithJwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/24
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean;

    @Autowired
    private UserDetailsService userDetailsService;

    // 装载BCrypt密码编码器
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager() ;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                // 允许首页
                .antMatchers("/").permitAll()
                // 允许登陆页
                .antMatchers("/toLogin").permitAll()

                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/**").permitAll()

                // 其他的则需要认证才能访问
                .anyRequest().authenticated();

        http.headers().cacheControl() ;
        http.formLogin()
                .usernameParameter("user")
                .passwordParameter("pwd")
                // 去往的登陆页面
                .loginPage("/toLogin")

                //去往的登陆的表单提交的url
                .loginProcessingUrl("/login") ;

        // 退出后，直接回到首页
        http.logout().logoutSuccessUrl("/") ;

        // 可以成功的退出，具体原因不知道
        http.csrf().disable() ;

        http.addFilterBefore(authenticationTokenFilterBean, UsernamePasswordAuthenticationFilter.class) ;

        // 不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 内存中定义好，用户名密码，加密方法
        /*auth
                        //密码加密方法
                .inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                        //内存中的用户名以及密码以及拥有的角色
                .withUser("yang").password(passwordEncoder().encode("yang")).roles("vip1","vip2","vip3")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("vip1");
        */

        /**
         * 装在 UserDetailsService 即 子类实现的 JwtUserDetailsServiceImpl
         */
        //这里可以是从数据库中查询出来的
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);

    }
}