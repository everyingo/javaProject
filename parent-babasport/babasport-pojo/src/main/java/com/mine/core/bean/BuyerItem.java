package com.mine.core.bean;

import java.io.Serializable;

import com.mine.core.bean.product.Sku;

public class BuyerItem implements Serializable {

	private static final long serialVersionUID = 2261742935548282975L;

	private Sku sku;

	private Boolean isHave = true;

	private Integer amount = 1;

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public Boolean getIsHave() {
		return isHave;
	}

	public void setIsHave(Boolean isHave) {
		this.isHave = isHave;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
