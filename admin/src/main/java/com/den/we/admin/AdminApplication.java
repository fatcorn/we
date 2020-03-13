package com.den.we.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @author fatKarin
 */
@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.den.we.admin.mapper")
@EnableEurekaClient
@EnableFeignClients
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600 * 24 * 30)
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
