package com.mine.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mine.core.common.tools.ComputeUtil;

public class BuyerCart implements Serializable {

	private static final long serialVersionUID = 1846596026504399554L;

	private List<BuyerItem> buyerItems;

	private Float total;

	public BuyerCart() {
		this.buyerItems = new ArrayList<>();
	}

	public List<BuyerItem> getBuyerItems() {
		return this.buyerItems;
	}

	public void addItems(BuyerItem buyerItem) {
		boolean isExist = false;
		for (BuyerItem bi : buyerItems) {
			if (buyerItem.getSku().getId() == bi.getSku().getId()) {
				bi.setAmount(bi.getAmount() + buyerItem.getAmount());
				isExist = true;
			}
		}
		if (!isExist)
			this.buyerItems.add(buyerItem);
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Float getDeliverFee() {
		if (getOrderPrice() <= 59) {
			return 10F;
		}
		return 0F;
	}

	public Float getOrderPrice() {
		Float orderPrice = 0F;
		for (BuyerItem bi : buyerItems) {
			orderPrice = ComputeUtil.add(orderPrice, bi.getSku().getPrice() * bi.getAmount());
		}
		return orderPrice;
	}

	public Float getTotalPrice() {
		return ComputeUtil.add(getDeliverFee(), getTotalPrice());
	}

	public Long[] getSkuIds() {
		Long[] skuIds = new Long[buyerItems.size()];
		for (int i = 0; i < buyerItems.size(); i++) {
			skuIds[i] = buyerItems.get(i).getSku().getId();
		}
		return skuIds;
	}

}
