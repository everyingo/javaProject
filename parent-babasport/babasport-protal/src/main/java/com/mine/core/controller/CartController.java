package com.mine.core.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mine.core.bean.BuyerCart;
import com.mine.core.bean.BuyerItem;
import com.mine.core.bean.product.Sku;
import com.mine.core.common.tools.CookieUtil;
import com.mine.core.common.tools.JsonUtil;
import com.mine.core.common.tools.RequestUtil;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.common.web.Constants;
import com.mine.core.service.cms.CmsService;
import com.mine.core.service.user.BuyerService;
import com.mine.core.service.user.SessionProvider;

@Controller
public class CartController {

	@Autowired
	private SessionProvider sessionProvider;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private CmsService cmsService;

	/**
	 * 添加购物车
	 * 
	 * @return
	 */
	@RequestMapping("/addCart")
	public String addCart(HttpServletRequest request, HttpServletResponse response, Long skuId, Integer amount) {
		BuyerCart buyerCart = getBuyerCartFromCookie(request);
		if (buyerCart == null) {
			buyerCart = new BuyerCart();
		}
		BuyerItem buyerItem = new BuyerItem();
		Sku sku = new Sku();
		sku.setId(skuId);
		buyerItem.setAmount(amount);
		buyerItem.setSku(sku);
		buyerCart.addItems(buyerItem);
		String username = getLoginUsername(request, response);
		if (username != null) {
			setBuyerCart2Redis(buyerCart, username);
			clearBuyerCartCookie(response);

		} else {
			setBuyerCart2Cookie(response, buyerCart);
		}
		return "redirect:/toCart";
	}

	/**
	 * 去购物车
	 * 
	 * @return
	 */
	@RequestMapping("/toCart")
	public String toCart(Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) {
		BuyerCart buyerCart = getBuyerCartFromCookie(request);
		String username = getLoginUsername(request, response);
		if (username != null) {
			if (buyerCart != null) {
				setBuyerCart2Redis(buyerCart, username);
				clearBuyerCartCookie(response);
			}
			buyerCart = getBuyerCartFromRedis(username);
		}
		// 购物车不为空填充商品信息
		if (buyerCart != null) {
			for (BuyerItem bi : buyerCart.getBuyerItems()) {
				Sku sku = bi.getSku();
				sku = cmsService.selectSkuById(sku.getId());
				sku.setColor(cmsService.selectColorById(sku.getColorId()));
				sku.setProduct(cmsService.selectProductById(sku.getProductId()));
				bi.setSku(sku);
			}
		}
		data.put("buyerCart", buyerCart);
		return "cart";
	}

	/**
	 * 从cookie中获取用户的购物车
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private BuyerCart getBuyerCartFromCookie(HttpServletRequest request) {
		BuyerCart buyerCart = null;
		String value = CookieUtil.getCookie(request, Constants.BUYER_CART_COOKIE);
		if (ValidateUtil.isValidate(value)) {
			buyerCart = JsonUtil.jsonstr2Object(value, BuyerCart.class);
		}
		return buyerCart;
	}

	/**
	 * 添加用户的购物车至cookie
	 * 
	 * @param response
	 * @param buyerCart
	 */
	private void setBuyerCart2Cookie(HttpServletResponse response, BuyerCart buyerCart) {
		String value = JsonUtil.object2Jsonstr(buyerCart);
		CookieUtil.setCookie(response, Constants.BUYER_CART_COOKIE, value, 60 * 60 * 24, "/", "*.abc.com");
	}

	/**
	 * 清空cookie用户的购物车
	 * 
	 * @param response
	 */
	private void clearBuyerCartCookie(HttpServletResponse response) {
		CookieUtil.clearCookie(response, Constants.BUYER_CART_COOKIE, "/", "*.abc.com");
	}

	/**
	 * 从Redis中获取用户的购物车
	 * 
	 * @param username
	 * @return
	 */
	private BuyerCart getBuyerCartFromRedis(String username) {
		return buyerService.getBuyerCartByUsername(username);
	}

	/**
	 * 添加用户的购物车至Redis
	 * 
	 * @param buyerCart
	 * @param username
	 */
	private void setBuyerCart2Redis(BuyerCart buyerCart, String username) {
		buyerService.setBuyerCartByUsername(buyerCart, username);
	}

	/**
	 * 获取当前登录的用户名称
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String getLoginUsername(HttpServletRequest request, HttpServletResponse response) {
		return sessionProvider.getAttributeForUsername(RequestUtil.getCSESSIONID(request, response));
	}

}
