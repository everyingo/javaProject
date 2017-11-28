package com.mine.core.service.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mine.core.bean.BuyerCart;
import com.mine.core.bean.BuyerItem;
import com.mine.core.bean.order.Detail;
import com.mine.core.bean.order.Order;
import com.mine.core.bean.user.Buyer;
import com.mine.core.dao.order.DetailDao;
import com.mine.core.dao.order.OrderDao;
import com.mine.core.dao.product.SkuDao;
import com.mine.core.redis.user.BuyerCartRedis;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private SkuDao skuDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private DetailDao detailDao;

	@Autowired
	private BuyerCartRedis buyerCartRedis;

	@Override
	@Transactional
	public boolean insertOrder(BuyerCart buyerCart, Order order, Buyer buyer) {
		boolean flag = false;

		order.setId(1L);
		for (BuyerItem bi : buyerCart.getBuyerItems()) {
			// 减库存
			int count = skuDao.updateStockByPrimaryKey(bi.getSku().getId(), bi.getAmount());
			if (count == 0)
				throw new RuntimeException();
			// 添加订单详情
			Detail detail = new Detail();
			detail.setId(1L);
			detail.setAmount(bi.getAmount());
			detail.setColor(bi.getSku().getColor().getName());
			detail.setOrderId(order.getId());
			detail.setPrice(bi.getSku().getPrice() * bi.getAmount());
			detail.setProductId(bi.getSku().getProductId());
			detail.setProductName(bi.getSku().getProduct().getName());
			detail.setSize(bi.getSku().getSize());
			detailDao.insertSelective(detail);
		}
		// 添加订单
		order.setBuyerId(buyer.getId());
		order.setCreateDate(new Date());
		order.setOrderPrice(buyerCart.getOrderPrice());
		order.setOrderState(0);
		order.setTotalPrice(buyerCart.getTotalPrice());
		order.setDeliverFee(buyerCart.getDeliverFee());
		if (order.getIsPaiy() == null) {
			order.setIsPaiy(1);
		}
		orderDao.insert(order);
		// 减购物车
		buyerCartRedis.subtractBuyerCartByUsername(buyer.getUsername(), buyerCart.getSkuIds());

		return flag;
	}

}
