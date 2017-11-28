package com.mine.core.redis.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mine.core.common.redis.BuyerConstants;

import redis.clients.jedis.Jedis;

@Component("buyerRedis")
public class BuyerRedis {

	@Autowired
	private Jedis jedis;

	/**
	 * setBuyerForUsername
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public boolean setBuyerForUsername(String name, String value) {
		String key = name + "-" + BuyerConstants.BUYER_NAME;
		jedis.set(key, value);
		jedis.expire(key, BuyerConstants.CSESSION_TIME_MIN * 60);
		return false;
	}

	/**
	 * getBuyerForUsername
	 * 
	 * @param name
	 * @return
	 */
	public String getBuyerForUsername(String name) {
		String key = name + "-" + BuyerConstants.BUYER_NAME;
		String value = jedis.get(key);
		if (value != null) {
			jedis.expire(key, BuyerConstants.CSESSION_TIME_MIN * 60);
			return value;
		}
		return null;
	}
}
