package com.chp.comm.utils.redis;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.chp.comm.utils.redis.RedisProperties;

import redis.clients.jedis.JedisPoolConfig;

//@Configuration
public class RedisConfig {
	@Autowired
	private RedisProperties redisProperties;

	//@Bean
	public RedisClusterConfiguration redisClusterConfiguration() {
		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
		if (this.isEmpty(this.redisProperties.getNodes())) {
			return redisClusterConfiguration;
		} else {
			String[] nodes = this.redisProperties.getNodes().split("&");
			if (null != nodes && nodes.length > 0) {
				List<RedisNode> nodeList = this.getNodeList(nodes);
				redisClusterConfiguration.setClusterNodes(nodeList);
			}

			if (!this.isEmpty(this.redisProperties.getMaxRedirects())) {
				redisClusterConfiguration
						.setMaxRedirects(Integer.valueOf(this.redisProperties.getMaxRedirects()).intValue());
			}

			return redisClusterConfiguration;
		}
	}

	//@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		if (!this.isEmpty(this.redisProperties.getMaxTotal())) {
			jedisPoolConfig.setMaxTotal(Integer.valueOf(this.redisProperties.getMaxTotal()).intValue());
		}

		if (!this.isEmpty(this.redisProperties.getMaxIdle())) {
			jedisPoolConfig.setMaxIdle(Integer.valueOf(this.redisProperties.getMaxIdle()).intValue());
		}

		if (!this.isEmpty(this.redisProperties.getMaxWaitMillis())) {
			jedisPoolConfig.setMaxWaitMillis(Long.valueOf(this.redisProperties.getMaxWaitMillis()).longValue());
		}

		if (!this.isEmpty(this.redisProperties.getMinEvictableIdleTimeMillis())) {
			jedisPoolConfig.setMinEvictableIdleTimeMillis(
					Long.valueOf(this.redisProperties.getMinEvictableIdleTimeMillis()).longValue());
		}

		if (!this.isEmpty(this.redisProperties.getNumTestsPerEvictionRun())) {
			jedisPoolConfig.setNumTestsPerEvictionRun(
					Integer.valueOf(this.redisProperties.getNumTestsPerEvictionRun()).intValue());
		}

		if (!this.isEmpty(this.redisProperties.getTimeBetweenEvictionRunsMillis())) {
			jedisPoolConfig.setTimeBetweenEvictionRunsMillis(
					Long.valueOf(this.redisProperties.getTimeBetweenEvictionRunsMillis()).longValue());

		}

		if (!this.isEmpty(this.redisProperties.getTestOnBorrow())) {
			if ("true".equals(this.redisProperties.getTestOnBorrow())) {
				jedisPoolConfig.setTestOnBorrow(true);
			} else if ("false".equals(this.redisProperties.getTestOnBorrow())) {
				jedisPoolConfig.setTestOnBorrow(false);
			}
		}
		
		if(!this.isEmpty(this.redisProperties.getPassword())) {
//			jedisPoolConfig.set
		}

		return jedisPoolConfig;
	}

	//@Bean
	public JedisConnectionFactory jedisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration,
			JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration,
				jedisPoolConfig);

		if (!this.isEmpty(this.redisProperties.getTimeout())) {
			jedisConnectionFactory.setTimeout(Integer.valueOf(this.redisProperties.getTimeout()).intValue());
		}
		return jedisConnectionFactory;
	}

	//@Bean
	public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}

	public boolean isEmpty(String str) {
		return StringUtils.isEmpty(str) || StringUtils.isEmpty(str.trim());
	}

	private List<RedisNode> getNodeList(String[] nodes) {
		List<RedisNode> nodeList = new ArrayList<>();
		if (null != nodes && nodes.length > 0) {
			String[] arg2 = nodes;
			int arg3 = nodes.length;
			for (int arg4 = 0; arg4 < arg3; ++arg4) {
				String node = arg2[arg4];
				if (!this.isEmpty(node)) {
					RedisNode redisNode = this.getRedisNode(node);
					if (null != redisNode) {
						nodeList.add(redisNode);
					}
				}
			}
		}else {
			Assert.notNull(nodes, "redis nodes must not be null!");
		}
		return nodeList;
	}

	private RedisNode getRedisNode(String node) {
		String[] nodeObj = node.trim().split(",");
		if (null != nodeObj && nodeObj.length > 0) {
			String host = nodeObj[0];
			String port = nodeObj[1];
			return !this.isEmpty(host) && !this.isEmpty(port) ? new RedisNode(host, Integer.valueOf(port).intValue())
					: null;
		}else {
			Assert.notNull(nodeObj, "redis nodes is null!");
		}
		return null;
	}
}
