package team.benchem.demo.rediscache.conf;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.util.ArrayList;
import java.util.List;

@EnableCaching
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactor) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        CacheManager cacheManager = RedisCacheManager
                .builder(redisConnectionFactor)
                .cacheDefaults(cacheConfiguration)
                .build();
        return cacheManager;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, args) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(method.getName()).append("(");
            List<String> argNames = new ArrayList<>();
            for (Object obj : args) {
                argNames.add(obj.toString());
            }
            if(argNames.size() > 0){
                sb.append(String.join(",",argNames));
            }
            sb.append(")");
            return sb.toString();
        };
    }

}
