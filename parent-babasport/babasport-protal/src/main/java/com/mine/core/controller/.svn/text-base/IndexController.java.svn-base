package com.mine.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.mine.core.bean.product.Brand;
import com.mine.core.bean.product.Product;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.service.product.BrandService;
import com.mine.core.service.search.SearchService;

@Controller
public class IndexController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private BrandService brandService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index() {

		return "index";
	}

	/**
	 * 搜索
	 * 
	 * @return
	 * @throws SolrServerException
	 */
	@RequestMapping(value = "/search")
	public String search(Map<String, Object> data, Integer pageNo, String keyword, Long brandId, String price)
			throws SolrServerException {
		List<Brand> brands = brandService.selectBrandListFromRedis();
		StringBuffer params = new StringBuffer("/search?");
		Map<String, String> filterQuery = new HashMap<>();
		keyword = (ValidateUtil.isValidate(keyword) ? keyword : "2016");
		params.append("keyword=" + keyword);
		if (brandId != null) {
			params.append("&brandId=" + brandId);
			for (Brand b : brands) {
				if (brandId == b.getId()) {
					filterQuery.put("品牌", b.getName());
				}
			}
		}
		if (ValidateUtil.isValidate(price)) {
			params.append("&price=" + price);
			if (price.contains("-")) {
				filterQuery.put("价格", price);
			} else {
				filterQuery.put("价格", price + "以上");
			}
		}

		PageInfo<Product> page = searchService.selectProductPageByQuery(pageNo, 12, keyword, brandId, price);
		data.put("page", page);
		data.put("brands", brands);
		data.put("pageUrl", params.toString());
		data.put("filterQuery", filterQuery);
		return "search";
	}

}
