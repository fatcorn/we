package com.den.we.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    /**
     * redis 防止key value 前缀乱码.
     *
     * @param factory redis连接 factory
     * @return redisTemplate
     */
    @Autowired
    private RedisTemplate redisTemplate;

   @Bean(name = "redisTemplate")
   public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//                 RedisTemplate<String, Object> template = new RedisTemplate<>();
//                 template.setConnectionFactory(factory);
//                 template.setKeySerializer(new StringRedisSerializer());
//                 template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//                 template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
//                 template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//                 template.afterPropertiesSet();
//                 return template;

               //设置序列化Key的实例化对象
               redisTemplate.setKeySerializer(new StringRedisSerializer());
               //设置序列化Value的实例化对象
               redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
               return redisTemplate;
               }
}
