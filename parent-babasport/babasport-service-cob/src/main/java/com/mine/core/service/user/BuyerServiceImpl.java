package com.mine.core.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.core.bean.BuyerCart;
import com.mine.core.bean.user.Buyer;
import com.mine.core.bean.user.BuyerQuery;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.dao.user.BuyerDao;
import com.mine.core.redis.user.BuyerCartRedis;

@Service("buyerService")
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerDao buyerDao;

	@Autowired
	private BuyerCartRedis buyerCartRedis;

	/**
	 * selectBuyerByUsername
	 * 
	 * @param username
	 * @return
	 */
	public Buyer selectBuyerByUsername(String username) {
		BuyerQuery buyerQuery = new BuyerQuery();
		buyerQuery.createCriteria().andUsernameEqualTo(username);
		List<Buyer> buyers = buyerDao.selectByExample(buyerQuery);
		if (ValidateUtil.isValidate(buyers)) {
			return buyers.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @param buyerCart
	 * @param username
	 */
	public void setBuyerCartByUsername(BuyerCart buyerCart, String username) {
		buyerCartRedis.setBuyerCartByUsername(buyerCart, username);
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public BuyerCart getBuyerCartByUsername(String username) {
		return buyerCartRedis.getBuyerCartByUsername(username);
	}
}
