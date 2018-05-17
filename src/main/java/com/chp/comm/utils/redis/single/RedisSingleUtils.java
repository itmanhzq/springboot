package com.chp.comm.utils.redis.single;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.chp.comm.utils.redis.CommRedisUtils;

@Component
public class RedisSingleUtils extends CommRedisUtils{
	protected final Logger logger = LoggerFactory.getLogger(RedisSingleUtils.class);
	/*@Autowired
	RedisTemplate<String,Object> redisTemplate;*/
	
}
