package com.mine.core.redis.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mine.core.bean.product.Brand;
import com.mine.core.common.redis.BrandConstants;

import redis.clients.jedis.Jedis;

@Component("brandRedis")
public class BrandRedis {

	@Autowired
	private Jedis jedis;

	/**
	 * selectBrandListFromRedis
	 * 
	 * @return
	 */
	public List<Brand> selectBrandListFromRedis() {
		List<Brand> brands = new ArrayList<>();
		Map<String, String> map = jedis.hgetAll(BrandConstants.BRANDS_HASH);
		for (String key : map.keySet()) {
			Brand brand = new Brand();
			brand.setId(Long.parseLong(key));
			brand.setName(map.get(key));
			brands.add(brand);
		}
		return brands;
	}

	/**
	 * insertBrand2Redis
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public long insertBrand2Redis(Long id, String name) {
		return jedis.hset(BrandConstants.BRANDS_HASH, String.valueOf(id), name);
	}

	/**
	 * deleteBrand2Redis
	 * 
	 * @param id
	 * @return
	 */
	public long deleteBrand2Redis(Long id) {
		return jedis.hdel(BrandConstants.BRANDS_HASH, String.valueOf(id));
	}

	/**
	 * deleteBrands2Redis
	 * 
	 * @param ids
	 * @return
	 */
	public long deleteBrands2Redis(Long[] ids) {
		String[] idsStr = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			idsStr[i] = String.valueOf(ids[i]);
		}
		return jedis.hdel(BrandConstants.BRANDS_HASH, idsStr);
	}
}
