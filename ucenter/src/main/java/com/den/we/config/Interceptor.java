package com.den.we.config;

import com.den.we.transform.AuthidUserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.den.we.constant.SessionAttribute.SESSION_USER_INFO;

/**
 * 拦截器
 *
 * @author fatKarin
 * @date 2019/8/29 17:04
 */
@Configuration
public class Interceptor implements WebMvcConfigurer {

    /**
     * 设置 相应内容编码格式
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login/login","/register/registerByPhone",
                        "/swagger-ui.html","/swagger-resources/**","/webjars/**", "/v2/**")
                ;
        //super(registry);
    }
}

class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        AuthidUserInfo user = (AuthidUserInfo)request.getSession().getAttribute(SESSION_USER_INFO);
        if (user != null) {
            return true;
        }
        return false;
    }
}