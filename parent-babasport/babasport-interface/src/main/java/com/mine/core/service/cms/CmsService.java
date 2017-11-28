package com.mine.core.service.cms;

import java.util.List;

import com.mine.core.bean.product.Color;
import com.mine.core.bean.product.Product;
import com.mine.core.bean.product.Sku;

public interface CmsService {

	/**
	 * selectProductById
	 * 
	 * @param productId
	 * @return
	 */
	public Product selectProductById(Long productId);

	/**
	 * selectSkuListProductById
	 * 
	 * @param productId
	 * @return
	 */
	public List<Sku> selectSkuListProductById(Long productId);

	/**
	 * selectSkuById
	 * 
	 * @param skuId
	 * @return
	 */
	public Sku selectSkuById(Long skuId);

	/**
	 * selectColorById
	 * 
	 * @param colorId
	 * @return
	 */
	public Color selectColorById(Long colorId);

}
