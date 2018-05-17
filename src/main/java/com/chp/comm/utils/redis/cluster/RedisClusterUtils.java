package com.chp.comm.utils.redis.cluster;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import com.chp.comm.ConfigProperties;
import com.chp.comm.utils.DateUtils;

//@Component
public class RedisClusterUtils {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;// 是RedisConfig类里的redisTemplate(JedisConnectionFactory
														// jedisConnectionFactory)方法？
	@Autowired
	private ConfigProperties configProperties;
	private static final long DEFAULT_EXPIRE = 30L;//默认过期时间是30秒
	private static final Logger logger = LoggerFactory.getLogger(RedisClusterUtils.class);

	public void set(String key, Object value) {
		if (StringUtils.isNotEmpty(key)) {
			this.redisTemplate.opsForValue().set(this.getKey(key), value);
		}
	}

	public void set(String key, Object value, long time) {
		if (StringUtils.isNotEmpty(key)) {
			this.set(key, value);
			this.expire(key, time);
		}
	}

	public Object get(String key) {
		return null != key ? this.redisTemplate.opsForValue().get(this.getKey(key)) : null;
	}

	public void del(String... key) {
		if (key != null && key.length > 0) {
			ArrayList<String> delKeys = new ArrayList<>();
			String[] arg2 = key;
			int arg3 = key.length;
			for (int arg4 = 0; arg4 < arg3; ++arg4) {
				String k = arg2[arg4];
				if (StringUtils.isNotBlank(k)) {
					delKeys.add(this.getKey(k));
				}
			}

			this.redisTemplate.delete(delKeys);
		}
	}

	public void expire(String key, long time) {
		if (time <= 0L) {
			time = DEFAULT_EXPIRE;
		}
		this.redisTemplate.expire(this.getKey(key), time, TimeUnit.SECONDS);
	}

	public long generate(String key) {
		RedisAtomicLong counter = new RedisAtomicLong(this.getKey(key), this.redisTemplate.getConnectionFactory());

		return counter.incrementAndGet();
	}

	public long generate(String key, Date expireTime) {
		RedisAtomicLong counter = new RedisAtomicLong(this.getKey(key), this.redisTemplate.getConnectionFactory());

		counter.expireAt(expireTime);
		return counter.incrementAndGet();
	}

	public long generate(String key, int increment) {
		RedisAtomicLong counter = new RedisAtomicLong(this.getKey(key), this.redisTemplate.getConnectionFactory());

		return counter.addAndGet((long) increment);
	}

	public long generate(String key, int increment, Date expireTime) {
		RedisAtomicLong counter = new RedisAtomicLong(this.getKey(key), this.redisTemplate.getConnectionFactory());

		counter.expireAt(expireTime);
		return counter.addAndGet((long) increment);
	}

	private StringBuilder getPreKey() {
		StringBuilder buffer = new StringBuilder("");
		String shortname = this.configProperties.getAppshortname();
		if (StringUtils.isNotBlank(shortname)) {
			buffer.append(shortname).append(":");
		}
		return buffer;
	}

	private String getKey(String key) {
		return StringUtils.isNotBlank(key) ? this.getPreKey().append(key).toString() : key;
	}

	public String generateLockKey(String key) {
		return String.format("LOCK:%s", new Object[] { this.getKey(key) });
	}

	public void setOrigin(String key, Object value) {
		if (null != key) {
			this.redisTemplate.opsForValue().set(key, value);
		}
	}

	public void setOrigin(String key, Object value, long time) {
		if (null != key) {
			this.setOrigin(key, value);
			if (time > 0L) {
				this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
		}

	}

	public Object getOrigin(String key) {
		return null != key ? this.redisTemplate.opsForValue().get(key) : null;
	}

	public void delOrigin(String... key) {
		if (key != null && key.length > 0) {
			ArrayList<String> delKeys = new ArrayList<>();
			String[] arg2 = key;
			int arg3 = key.length;
			for (int arg4 = 0; arg4 < arg3; ++arg4) {
				String k = arg2[arg4];
				if (StringUtils.isNotBlank(k)) {
					delKeys.add(k);
					this.redisTemplate.delete(k);
				}
			}

			this.redisTemplate.delete(delKeys);
		}
	}

	public boolean lock(String key) {
		return this.lock(key, 30L);
	}

	public boolean lock(String key, long time) {
		return this.lockOrigin(this.generateLockKey(key), time);
	}

	public void unlock(String key) {
		this.unlockOrigin(this.generateLockKey(key));
	}

	public boolean lockOrigin(String key, long time) {
		if (StringUtils.isBlank(key)) {
			return false;
		} else {
			try {
				RedisSerializer<String> e = this.redisTemplate.getStringSerializer();

				if (this.redisTemplate.getConnectionFactory().getConnection().setNX(e.serialize(key), new byte[0])
						.booleanValue()) {

					this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
					logger.debug("add RedisLock[" + key + "].");
					return true;
				}
			} catch (Exception arg4) {
				this.unlockOrigin(key);
			}
			return false;
		}
	}

	public void unlockOrigin(String key) {
		if (!StringUtils.isBlank(key)) {
			return;
		}
		logger.debug("release RedisLock[" + key + "].");
		RedisSerializer<String> serializer = this.redisTemplate.getStringSerializer();

		this.redisTemplate.getConnectionFactory().getConnection().del(new byte[][] { serializer.serialize(key) });

	}

	public boolean lockBusiness(String key, long time) {
		String lockKey = this.generateLockKey(key);
		if (this.lockOrigin(lockKey, time)) {
			this.set(lockKey, DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
			return true;
		} else {
			return false;
		}
	}

	public boolean isvalidLockBusiness(String key, long time) {
		String lockKey = this.generateLockKey(key);
		Object value = this.get(lockKey);
		if (null != value && !"".equals(value)) {
			try {
				long e = DateUtils
						.pastSeconds(DateUtils.parseDate(value.toString(), new String[] { "yyyy-MM-dd HH:mm:ss" }));

				if (e < time) {
					return true;
				}
			} catch (ParseException arg7) {
				return false;
			}
		}

		return false;
	}

	public void unlockBusiness(String key) {
		String lockKey = this.generateLockKey(key);
		this.unlockOrigin(lockKey);
		this.del(new String[] { lockKey });
	}
}
