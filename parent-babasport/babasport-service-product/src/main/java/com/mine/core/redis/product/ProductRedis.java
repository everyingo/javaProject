package com.mine.core.redis.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mine.core.common.redis.ProductConstants;

import redis.clients.jedis.Jedis;

@Component("productRedis")
public class ProductRedis {

	@Autowired
	private Jedis jedis;

	public long getProductNumber() {
		return jedis.incr(ProductConstants.PRODUCT_NUMBER);
	}

}
