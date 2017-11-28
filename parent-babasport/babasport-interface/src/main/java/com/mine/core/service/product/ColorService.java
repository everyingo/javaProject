package com.mine.core.service.product;

import java.util.List;

import com.mine.core.bean.product.Color;

public interface ColorService {

	/**
	 * 颜色集合
	 * 
	 * @return
	 */
	List<Color> selectColorList();
}
