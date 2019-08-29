package com.den.we.config;

//import com.spark.bitrade.ext.SmartHttpSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

import javax.servlet.http.HttpServlet;

//import static org.springframework.session.web.http.HeaderHttpSessionIdResolver.HEADER_AUTHENTICATION_INFO;
//import static org.springframework.session.web.http.HeaderHttpSessionIdResolver.HEADER_X_AUTH_TOKEN;
//import org.springframework.session.web.http.CookieHttpSessionStrategy;
//import org.springframework.session.web.http.HeaderHttpSessionStrategy;
//import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * 了解 HttpSessionIdResolver，待完成
 * 12小时过期
 */
@Configuration
//The @EnableRedisHttpSession annotation creates a Spring Bean with the name of springSessionRepositoryFilter that implements Filter. The filter is what is in charge of replacing the HttpSession implementation to be backed by Spring Session. In this instance Spring Session is backed by Redis.
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600 *12)
public class HttpSessionConfig {

	/**
	 * 将session cookie 储存改为 头部 储存
	 * @return
	 */
	@Bean
	public HeaderHttpSessionIdResolver httpSessionStrategy() {
		return new HeaderHttpSessionIdResolver("x-auth-token");
	}

	/**
	 * redis 相关配置，晓不得卵求用，先放到
	 * @return
	 */
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

}


