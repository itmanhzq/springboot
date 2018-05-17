package com.chp.comm.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("redisPropertiesFactory")
public class RedisProperties {
	private String maxRedirects;
	private String maxTotal;
	private String maxIdle;
	private String minIdle;
	private String maxWaitMillis;
	private String minEvictableIdleTimeMillis;
	private String numTestsPerEvictionRun;
	private String timeBetweenEvictionRunsMillis;
	private String testOnBorrow;
	private String timeout;
	private String usePool;
	private String password;
	private String nodes;
	@Autowired
	private Environment env;

	@Bean
	public RedisProperties redisProperties() {
		RedisProperties properties = new RedisProperties();
		this.maxRedirects = this.env.getProperty("default.redis.maxRedirects");
		this.maxTotal = this.env.getProperty("default.redis.maxTotal");
		this.maxIdle = this.env.getProperty("default.redis.maxIdle");
		this.minIdle = this.env.getProperty("default.redis.minIdle");
		this.maxWaitMillis = this.env.getProperty("default.redis.maxWaitMillis");
		this.minEvictableIdleTimeMillis = this.env.getProperty("default.redis.minEvictableIdleTimeMillis");

		this.numTestsPerEvictionRun = this.env.getProperty("default.redis.numTestsPerEvictionRun");

		this.timeBetweenEvictionRunsMillis = this.env.getProperty("default.redis.timeBetweenEvictionRunsMillis");

		this.testOnBorrow = this.env.getProperty("default.redis.testOnBorrow");
		this.timeout = this.env.getProperty("default.redis.timeout");
		this.usePool = this.env.getProperty("default.redis.usePool");
		this.nodes = this.env.getProperty("default.redis.nodes");
		this.password = this.env.getProperty("default.redis.password");

		properties.setMaxRedirects(this.maxRedirects);
		properties.setMaxTotal(this.maxTotal);
		properties.setMaxIdle(this.maxIdle);
		properties.setMinIdle(this.minIdle);
		properties.setMaxWaitMillis(this.maxWaitMillis);
		properties.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
		properties.setNumTestsPerEvictionRun(this.numTestsPerEvictionRun);
		properties.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);

		properties.setTestOnBorrow(this.testOnBorrow);
		properties.setTimeout(this.timeout);
		properties.setUsePool(this.usePool);
		properties.setPassword(this.password);
		properties.setNodes(this.nodes);
		return properties;
	}

	public String getMaxRedirects() {
		return this.maxRedirects;
	}

	public void setMaxRedirects(String maxRedirects) {
		this.maxRedirects = maxRedirects;
	}

	public String getMaxTotal() {
		return this.maxTotal;
	}

	public void setMaxTotal(String maxTotal) {
		this.maxTotal = maxTotal;
	}

	public String getMaxIdle() {
		return this.maxIdle;
	}

	public void setMaxIdle(String maxIdle) {
		this.maxIdle = maxIdle;
	}

	public String getMinIdle() {
		return this.minIdle;
	}

	public void setMinIdle(String minIdle) {
		this.minIdle = minIdle;
	}

	public String getMaxWaitMillis() {
		return this.maxWaitMillis;
	}

	public void setMaxWaitMillis(String maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public String getMinEvictableIdleTimeMillis() {
		return this.minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public String getNumTestsPerEvictionRun() {
		return this.numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(String numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public String getTimeBetweenEvictionRunsMillis() {
		return this.timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public String getTestOnBorrow() {
		return this.testOnBorrow;
	}

	public void setTestOnBorrow(String testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public String getTimeout() {
		return this.timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getUsePool() {
		return this.usePool;
	}

	public void setUsePool(String usePool) {
		this.usePool = usePool;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNodes() {
		return this.nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	public boolean validQueue() {
		return true;
	}
}
