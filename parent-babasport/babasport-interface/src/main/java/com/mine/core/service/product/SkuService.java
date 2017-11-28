package com.mine.core.service.product;

import java.util.List;

import com.mine.core.bean.product.Sku;

public interface SkuService {

	/**
	 * 查询库存信息
	 * 
	 * @param productId
	 * @return
	 */
	List<Sku> selectSkuListByProductId(Long productId);

	/**
	 * 修改库存信息
	 * 
	 * @param sku
	 * @return
	 */
	boolean updateSku(Sku sku);
}
