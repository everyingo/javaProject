package com.mine.core.redis.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mine.core.bean.BuyerCart;
import com.mine.core.bean.BuyerItem;
import com.mine.core.bean.product.Sku;
import com.mine.core.common.redis.BuyerCartConstants;
import com.mine.core.common.tools.ValidateUtil;

import redis.clients.jedis.Jedis;

@Component("buyerCartRedis")
public class BuyerCartRedis {

	@Autowired
	private Jedis jedis;

	/**
	 * setBuyerCartByUsername
	 * 
	 * @param buyerCart
	 * @param username
	 */
	public void setBuyerCartByUsername(BuyerCart buyerCart, String username) {
		String key = username + "-" + BuyerCartConstants.BUYER_CART_NAME;
		if (buyerCart != null) {
			List<BuyerItem> items = buyerCart.getBuyerItems();
			for (BuyerItem bi : items) {
				// jedis.hset(key, String.valueOf(bi.getSku().getId()),
				// String.valueOf(bi.getAmount()));
				// 同款商品数量合并
				jedis.hincrBy(key, String.valueOf(bi.getSku().getId()), bi.getAmount());
			}
		}
	}

	/**
	 * getBuyerCartByUsername
	 * 
	 * @param username
	 * @return
	 */
	public BuyerCart getBuyerCartByUsername(String username) {
		BuyerCart buyerCat = null;
		String key = username + "-" + BuyerCartConstants.BUYER_CART_NAME;
		Map<String, String> map = jedis.hgetAll(key);
		if (ValidateUtil.isValidate(map)) {
			buyerCat = new BuyerCart();
			for (String skuid : map.keySet()) {
				Sku sku = new Sku();
				sku.setId(Long.parseLong(skuid));

				BuyerItem bi = new BuyerItem();
				bi.setSku(sku);
				bi.setAmount(Integer.parseInt(map.get(skuid)));

				buyerCat.addItems(bi);
			}
		}
		return buyerCat;
	}

	/**
	 * subtractBuyerCartByUsername
	 * 
	 * @param username
	 */
	public void subtractBuyerCartByUsername(String username, Long[] skuIds) {
		String key = username + "-" + BuyerCartConstants.BUYER_CART_NAME;
		if (ValidateUtil.isValidate(skuIds)) {
			String[] skuIdsStr = new String[skuIds.length];
			for (int i = 0; i < skuIds.length; i++) {
				skuIdsStr[i] = String.valueOf(skuIds[i]);
			}
			jedis.hdel(key, skuIdsStr);
		}
	}

}
