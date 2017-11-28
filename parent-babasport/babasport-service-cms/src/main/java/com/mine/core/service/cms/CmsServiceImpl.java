package com.mine.core.service.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.core.bean.product.Color;
import com.mine.core.bean.product.Product;
import com.mine.core.bean.product.Sku;
import com.mine.core.bean.product.SkuQuery;
import com.mine.core.dao.product.ColorDao;
import com.mine.core.dao.product.ProductDao;
import com.mine.core.dao.product.SkuDao;

@Service("cmsService")
public class CmsServiceImpl implements CmsService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private SkuDao skuDao;

	@Autowired
	private ColorDao colorDao;

	@Override
	public Product selectProductById(Long productId) {
		return productDao.selectByPrimaryKey(productId);
	}

	@Override
	public List<Sku> selectSkuListProductById(Long productId) {
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(productId).andPriceGreaterThan(0f);
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		for (Sku sku : skus) {
			sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		}
		return skus;
	}

	@Override
	public Sku selectSkuById(Long skuId) {
		return skuDao.selectByPrimaryKey(skuId);
	}

	@Override
	public Color selectColorById(Long colorId) {
		return colorDao.selectByPrimaryKey(colorId);
	}
}
