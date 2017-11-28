package com.mine.core.service.search;

import org.apache.solr.client.solrj.SolrServerException;

import com.github.pagehelper.PageInfo;
import com.mine.core.bean.product.Product;

public interface SearchService {

	/**
	 * solr数据库中分页查询商品
	 * 
	 * @param pageNo
	 * @param keyword
	 * @param BrandId
	 * @param price
	 * @return
	 */
	PageInfo<Product> selectProductPageByQuery(Integer pageNo,Integer pageSize, String keyword, Long brandId, String price)
			throws SolrServerException;
}
