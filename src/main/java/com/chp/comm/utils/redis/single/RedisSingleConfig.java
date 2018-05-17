package com.chp.comm.utils.redis.single;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;
@Configuration
public class RedisSingleConfig {
	
	protected final Logger logger = LoggerFactory.getLogger(RedisSingleConfig.class);
	
	  //获取springboot配置文件的值 (get的时候获取)
		//  @Value("${default.redis.password}")  
	    private final String HOST="193.112.80.60"; 
	    private final int PORT=6379;
	    private String PASSWORD="w0deredis!";
	  
	  
	    /** 
	     * @Bean 和 @ConfigurationProperties 
	     * 该功能在官方文档是没有提到的，我们可以把@ConfigurationProperties和@Bean和在一起使用。 
	     * 举个例子，我们需要用@Bean配置一个Config对象，Config对象有a，b，c成员变量需要配置， 
	     * 那么我们只要在yml或properties中定义了a=1,b=2,c=3， 
	     * 然后通过@ConfigurationProperties就能把值注入进Config对象中 
	     * @return 
	     */  
	    @Bean  
	   // @ConfigurationProperties(prefix = "spring.redis.pool")  
	    public JedisPoolConfig getRedisConfig() {  
	        JedisPoolConfig config = new JedisPoolConfig();  
	        config.setMaxIdle(10);
	        config.setMaxTotal(20);
	        config.setMaxWaitMillis(3000);
	        config.setMinEvictableIdleTimeMillis(-1);
	        config.setNumTestsPerEvictionRun(3);
	        config.setTimeBetweenEvictionRunsMillis(-1);
	        config.setTestOnBorrow(false);
	        return config;  
	    }  
	  
	    @Bean  
	 //   @ConfigurationProperties(prefix = "singlenode.redis")  
	    public JedisConnectionFactory getConnectionFactory() {  
	        JedisConnectionFactory factory = new JedisConnectionFactory();  
	        factory.setUsePool(true);  
	        factory.setHostName(HOST);
	        factory.setPort(PORT);
	        factory.setPassword(PASSWORD);
//	        factory.setDatabase(2);
	        factory.setTimeout(3000);
	        JedisPoolConfig config = getRedisConfig();
	        factory.setPoolConfig(config);  
	        logger.info("JedisConnectionFactory bean init success...");  
	        return factory;  
	    }  
	  
	    @Bean  
	    public RedisTemplate<String,Object> redisTemplate() {  
	        JedisConnectionFactory jedisConnectionFactory = getConnectionFactory();  
	        logger.info(HOST+","+jedisConnectionFactory.getHostName()+","+jedisConnectionFactory.getDatabase());  
	        logger.info(PASSWORD+","+jedisConnectionFactory.getPassword());  
	        logger.info("MaxIdle: {}",jedisConnectionFactory.getPoolConfig().getMaxIdle());  
	        
	        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();  
	        redisTemplate.setConnectionFactory(jedisConnectionFactory);
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			//redisTemplate.setValueSerializer(new StringRedisSerializer());//value有多种数据类型，不设
	        return redisTemplate;
	        
	    	/*Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
	        ObjectMapper om = new ObjectMapper();
	        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	        jackson2JsonRedisSerializer.setObjectMapper(om);
	        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	        template.setConnectionFactory(redisConnectionFactory);
	        template.setKeySerializer(jackson2JsonRedisSerializer);
	        template.setValueSerializer(jackson2JsonRedisSerializer);
	        template.setHashKeySerializer(jackson2JsonRedisSerializer);
	        template.setHashValueSerializer(jackson2JsonRedisSerializer);
	        template.afterPropertiesSet();
	        return template;*/
	    }
}
