package com.mine.core.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.core.redis.user.BuyerRedis;

@Service("sessionProvider")
public class SessionProviderImpl implements SessionProvider {

	@Autowired
	private BuyerRedis buyerRedis;

	/**
	 * setAttribuerForUsername
	 */
	@Override
	public void setAttribuerForUsername(String name, String value) {
		buyerRedis.setBuyerForUsername(name, value);
	}

	/**
	 * getAttributeForUsername
	 */
	@Override
	public String getAttributeForUsername(String name) {

		return buyerRedis.getBuyerForUsername(name);
	}

}
