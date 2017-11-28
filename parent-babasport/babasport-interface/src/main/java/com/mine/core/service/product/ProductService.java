package com.mine.core.service.product;

import com.github.pagehelper.PageInfo;
import com.mine.core.bean.product.Product;

public interface ProductService {

	/**
	 * 分页查询商品
	 * 
	 * @param productQuery
	 * @return
	 */
	PageInfo<Product> selectPageByQuery(Integer pageNo, String name, Long brandId, Boolean isShow);

	/**
	 * 添加商品
	 * 
	 * @param product
	 */
	boolean insertProduct(Product product);

	/**
	 * 上架商品批量
	 * 
	 * @param ids
	 */
	boolean isShow(Long[] ids);

}
