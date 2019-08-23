package com.den.we.config;

//import com.spark.bitrade.ext.SmartHttpSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

//import static org.springframework.session.web.http.HeaderHttpSessionIdResolver.HEADER_AUTHENTICATION_INFO;
//import static org.springframework.session.web.http.HeaderHttpSessionIdResolver.HEADER_X_AUTH_TOKEN;
//import org.springframework.session.web.http.CookieHttpSessionStrategy;
//import org.springframework.session.web.http.HeaderHttpSessionStrategy;
//import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * 12小时过期
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600 *12)
public class HttpSessionConfig {

	/**
	 * 增加相应头
	 * @return
	 */
	@Bean
	public HeaderHttpSessionIdResolver httpSessionStrategy() {
		return new HeaderHttpSessionIdResolver("x-auth-token");
	}
}
