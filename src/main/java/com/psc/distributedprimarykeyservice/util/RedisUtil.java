package com.psc.distributedprimarykeyservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisUtil {

	@Autowired
	private JedisPool jedisPool;
	
	public Long incr(String key, int timeout){
		/**
		 * incr(key)是redis的一个同步方法，用于对key自增加1；当key不存在时，则创建值为0的key。
		 **/
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();//拿到jedis客户端
			Long id = jedis.incr(key);//+1
			if(timeout>0){
				jedis.expire(key, timeout);//超时
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null){
				jedis.close();
			}
		}
		return null;
	}

	@Bean
	JedisPool jedisPool(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(2000);
		jedisPoolConfig.setMaxIdle(100);
		jedisPoolConfig.setMaxWaitMillis(1000);
		jedisPoolConfig.setTestOnBorrow(true);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);
		return jedisPool;
	}
}
