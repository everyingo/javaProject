package com.mine.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.core.bean.product.Sku;
import com.mine.core.bean.product.SkuQuery;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.dao.product.ColorDao;
import com.mine.core.dao.product.SkuDao;

@Service("skuService")
public class SkuServiceImpl implements SkuService {

	@Autowired
	private SkuDao skuDao;

	@Autowired
	private ColorDao colorDao;

	@Override
	public List<Sku> selectSkuListByProductId(Long productId) {
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(productId);
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		if (ValidateUtil.isValidate(skus)) {
			for (Sku sku : skus) {
				sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
			}
		}
		return skus;
	}

	@Override
	public boolean updateSku(Sku sku) {
		return skuDao.updateByPrimaryKeySelective(sku) > 0;
	}

}
