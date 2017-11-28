package com.mine.core.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mine.core.bean.product.Color;
import com.mine.core.bean.product.Product;
import com.mine.core.bean.product.Sku;
import com.mine.core.service.cms.CmsService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private CmsService cmsService;

	/**
	 * 商品详情【从数据库中查询】
	 * 
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(Map<String, Object> data, Long productId) {
		Product product = cmsService.selectProductById(productId);
		List<Sku> skus = cmsService.selectSkuListProductById(productId);
		Set<Color> colors = new HashSet<>();
		for (Sku sku : skus) {
			colors.add(sku.getColor());
		}
		data.put("product", product);
		data.put("skus", skus);
		data.put("colors", colors);
		return "product";
	}
}
