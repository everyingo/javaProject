package com.mine.core.controller.buyer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mine.core.bean.BuyerCart;
import com.mine.core.bean.BuyerItem;
import com.mine.core.bean.order.Order;
import com.mine.core.bean.product.Sku;
import com.mine.core.bean.user.Buyer;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.service.cms.CmsService;
import com.mine.core.service.user.OrderService;

@Controller
@RequestMapping("/buyer")
public class OrderController {

	@Autowired
	private CmsService cmsService;

	@Autowired
	private OrderService orderService;

	/**
	 * 去结算
	 * 
	 * @return
	 */
	@RequestMapping("/toOrders")
	public String toOrders(Map<String, Object> data, BuyerItem[] buyerItem) {
		// 检查库存
		if (!isHave(buyerItem)) {
			return "redirect:/toCart";
		}
		BuyerCart buyerCart = new BuyerCart();
		for (BuyerItem bi : buyerItem) {
			Sku sku = bi.getSku();
			sku = cmsService.selectSkuById(sku.getId());
			sku.setColor(cmsService.selectColorById(sku.getColorId()));
			sku.setProduct(cmsService.selectProductById(sku.getProductId()));
			bi.setSku(sku);
			buyerCart.addItems(bi);
		}
		data.put("buyerCart", buyerCart);
		return "order";
	}

	/**
	 * 提交订单
	 * 
	 * @return
	 */
	@RequestMapping("/addOrders")
	public String addOrders(BuyerItem[] buyerItem, Order order) {
		Buyer buyer = new Buyer();
		buyer.setId(100L);
		buyer.setUsername("uname");
		BuyerCart buyerCart = new BuyerCart();
		for (BuyerItem bi : buyerCart.getBuyerItems()) {
			Sku sku = bi.getSku();
			sku = cmsService.selectSkuById(sku.getId());
			sku.setColor(cmsService.selectColorById(sku.getColorId()));
			sku.setProduct(cmsService.selectProductById(sku.getProductId()));
			bi.setSku(sku);
		}
		boolean flag = orderService.insertOrder(buyerCart, order, buyer);
		if (!flag) {
			return "";
		}
		return "success";
	}

	private boolean isHave(BuyerItem[] buyerItem) {
		if (ValidateUtil.isValidate(buyerItem)) {
			for (BuyerItem bi : buyerItem) {
				Sku sku = cmsService.selectSkuById(bi.getSku().getId());
				if (sku.getStock() < bi.getAmount()) {
					return false;
				}
			}
		}
		return true;
	}
}
