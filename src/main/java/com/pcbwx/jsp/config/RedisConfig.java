package com.pcbwx.jsp.config;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcbwx.jsp.model.Dictionary;

/**
 * Redis配置类
 *
 * @author 孙贺宇
 * @date 2018-09-01
 * 
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
		return container;
	}

    @Bean
	@Override
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	Receiver receiver(CountDownLatch latch) {
		return new Receiver(latch);
	}

	@Bean
	CountDownLatch latch() {
		return new CountDownLatch(1);
	}

    public class Receiver {
        private CountDownLatch latch;

        @Autowired
        public Receiver(CountDownLatch latch) {
            this.latch = latch;
        }

        public void receiveMessage(String message) {
            latch.countDown();
        }
    }

    /**
     * 创建redis操作实例
     * @param clazz		泛型类
     * @param connectionFactory redis连接工厂
     * @return
     */
    private <T> RedisTemplate<String, T> createTemplate(Class<T> clazz, RedisConnectionFactory connectionFactory){
        Jackson2JsonRedisSerializer<T> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<T>(
                clazz);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, T> template = new RedisTemplate<String, T>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    // --------------------注入操作类---------------------------

	@Bean
	StringRedisTemplate stringTemplate(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}

	@Bean
	RedisTemplate<String, Object> template(RedisConnectionFactory connectionFactory) {
		return createTemplate(Object.class, connectionFactory);
	}

	@Bean
	RedisTemplate<String, Dictionary> templateDic(RedisConnectionFactory connectionFactory) {
		return createTemplate(Dictionary.class, connectionFactory);
	}
}
