package com.den.we;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
/*@EnableAutoConfiguration*/
@SpringCloudApplication
public class GetawayApplication {
    public static void main(String[] args){
        SpringApplication.run(GetawayApplication.class,args);
    }
}
