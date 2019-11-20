package com.den.we.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {


    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    /**  redis-cache configuration */
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 7 天缓存过期
                .entryTtl(Duration.ofDays(7))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }

    // 使用自定义redis 序列化类
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new CustomFastJsonRedisSerializer();
    }

    /**
     * 自定义redis序列化类
     * <function>序列化对象时加入 对象类信息,反序列化时利用反射解析对象<function/>
     */
    class CustomFastJsonRedisSerializer implements RedisSerializer<Object> {

        private FastJsonConfig fastJsonConfig = new FastJsonConfig();


        public FastJsonConfig getFastJsonConfig() {
            return this.fastJsonConfig;
        }

        public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
            this.fastJsonConfig = fastJsonConfig;
        }

        public byte[] serialize(Object t) throws SerializationException {
            if (t == null) {
                return new byte[0];
            } else {
                try {
                    Map<String,Object> objMap = new HashMap<>();
                    objMap.put("objClass", t.getClass().getName());
                    objMap.put("obj", t);

                    return JSON.toJSONBytes(objMap, this.fastJsonConfig.getSerializeConfig(), this.fastJsonConfig.getSerializerFeatures());
                } catch (Exception var3) {
                    throw new SerializationException("Could not serialize: " + var3.getMessage(), var3);
                }
            }
        }

        public Object deserialize(byte[] bytes) throws SerializationException {
            if (bytes != null && bytes.length != 0) {
                try {
                    Map objMap = JSON.parseObject(bytes,Map.class);
                    Class<?> obj = Class.forName(String.valueOf(objMap.get("objClass")));
                    return JSON.parseObject(String.valueOf(objMap.get("obj")),obj);

                } catch (Exception var3) {
                    throw new SerializationException("Could not deserialize: " + var3.getMessage(), var3);
                }
            } else {
                return null;
            }
        }
    }
}


