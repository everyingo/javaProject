package com.mine.core.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mine.core.bean.product.Sku;
import com.mine.core.service.product.SkuService;

@Controller
@RequestMapping("/sku")
public class SkuController {

	@Autowired
	private SkuService skuService;

	/**
	 * 去修改库存
	 * 
	 * @param data
	 * @param productId
	 * @return
	 */
	@RequestMapping("/toSku.do")
	public String toSku(Map<String, Object> data, Long productId) {
		List<Sku> skus = skuService.selectSkuListByProductId(productId);
		data.put("skus", skus);
		return "sku/list";
	}

	/**
	 * 修改库存
	 * 
	 * @param sku
	 * @return
	 */
	@RequestMapping("/addSku.do")
	@ResponseBody
	public JSONObject addSku(Sku sku) {
		JSONObject jo = new JSONObject();
		boolean isSuccess = skuService.updateSku(sku);
		if (isSuccess) {
			jo.put("message", "success");
		} else {
			jo.put("message", "error");
		}
		return jo;
	}
}
