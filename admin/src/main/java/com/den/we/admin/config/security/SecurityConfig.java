package com.den.we.admin.config.security;

import com.den.we.admin.config.security.AuthFailedHandler;
import com.den.we.admin.config.security.AuthSuccessHandler;
import com.den.we.admin.config.security.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * security 配置
 * @author fatKarin
 * @date 2020/3/9 9:40
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/test/addSuperAdmin").permitAll()
                .anyRequest().authenticated()
        .and()
                .formLogin()
                .successHandler(new AuthSuccessHandler())
                .failureHandler(new AuthFailedHandler())
        .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        .and()
                .csrf().disable().cors()
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       super.configure(auth);
    }

    @Bean //加密
    public PasswordEncoder passwordEncoder() {
        //如果系统本身有了其他的加密方式，此处就应该返回自己写的passwordencoder，再实现encoder和matches方法
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:9204"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.addAllowedOrigin("*");//修改为添加而不是设置，* 最好改为实际的需要，我这是非生产配置，所以粗暴了一点
        configuration.addAllowedMethod("*");//修改为添加而不是设置
        configuration.addAllowedHeader("*");//这里很重要，起码需要允许 Access-Control-Allow-Origin
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Location"); // 暴露 Location header
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
