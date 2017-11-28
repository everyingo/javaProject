package com.mine.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.mine.core.bean.product.Brand;
import com.mine.core.bean.product.Color;
import com.mine.core.bean.product.Product;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.service.product.BrandService;
import com.mine.core.service.product.ColorService;
import com.mine.core.service.product.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ColorService colorService;

	/**
	 * 商品列表
	 * 
	 * @param data
	 * @param pageNo
	 * @param name
	 * @param brandId
	 * @param isShow
	 * @return
	 */
	@RequestMapping("/list.do")
	public String list(Map<String, Object> data, Integer pageNo, String name, Long brandId, Boolean isShow) {
		StringBuffer params = new StringBuffer("/product/list.do?");
		if (ValidateUtil.isValidate(name)) {
			params.append("name=" + name);
		}
		if (brandId != null) {
			params.append("&brandId=" + brandId);
		}
		if (isShow != null) {
			params.append("&isShow=" + isShow);
		} else {
			params.append("&isShow=" + false);
		}
		PageInfo<Product> page = productService.selectPageByQuery(pageNo, name, brandId, isShow);
		List<Brand> brands = brandService.selectBrandByIsDispaly(true);

		data.put("name", name);
		data.put("brandId", brandId);
		data.put("isShow", isShow);
		data.put("pageNo", pageNo);
		data.put("pageUrl", params.toString());
		data.put("page", page);
		data.put("brands", brands);

		return "product/list";
	}

	/**
	 * 去添加商品
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("/toAdd.do")
	public String toAdd(Map<String, Object> data) {
		List<Brand> brands = brandService.selectBrandByIsDispaly(true);
		List<Color> colors = colorService.selectColorList();
		data.put("brands", brands);
		data.put("colors", colors);
		return "product/add";
	}

	/**
	 * 添加商品
	 * 
	 * @return
	 */
	@RequestMapping("/add.do")
	public String add(Product product) {
		productService.insertProduct(product);
		return "redirect:/product/list.do";
	}

	@RequestMapping("/isShow.do")
	public String isShow(Long[] ids) {
		productService.isShow(ids);
		return "forward:/product/list.do";
	}
}
