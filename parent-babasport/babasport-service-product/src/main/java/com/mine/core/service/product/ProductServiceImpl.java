package com.mine.core.service.product;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mine.core.common.tools.PageValueUtil;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.dao.product.ProductDao;
import com.mine.core.dao.product.SkuDao;
import com.mine.core.redis.product.ProductRedis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mine.core.bean.product.Product;
import com.mine.core.bean.product.ProductQuery;
import com.mine.core.bean.product.Sku;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private SkuDao skuDao;

	@Autowired
	private ProductRedis productRedis;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public PageInfo<Product> selectPageByQuery(Integer pageNo, String name, Long brandId, Boolean isShow) {
		PageHelper.startPage(PageValueUtil.checkPageNumber(pageNo), PageValueUtil.checkPageSize(null));
		ProductQuery productQuery = new ProductQuery();
		ProductQuery.Criteria criteria = productQuery.createCriteria();
		if (ValidateUtil.isValidate(name)) {
			criteria.andNameLike("%" + name + "%");
		}
		if (brandId != null) {
			criteria.andBrandIdEqualTo(brandId);
		}
		if (isShow != null) {
			criteria.andIsShowEqualTo(isShow);
		} else {
			criteria.andIsShowEqualTo(false);
		}
		return new PageInfo<Product>(productDao.selectByExample(productQuery));
	}

	@Override
	@Transactional
	public boolean insertProduct(Product product) {
		int count = 0;
		if (product != null) {
			Long id = productRedis.getProductNumber();
			product.setId(id);
			if (product.getIsNew() == null) {
				product.setIsNew(false);
			}
			if (product.getIsHot() == null) {
				product.setIsHot(false);
			}
			if (product.getIsCommend() == null) {
				product.setIsCommend(false);
			}
			product.setIsShow(false);
			product.setIsDel(false);
			product.setCreateTime(new Date());
			count = productDao.insert(product);
			if (ValidateUtil.isValidate(product.getColors(), product.getSizes())) {
				String[] colors = product.getColors().split(",");
				String[] sizes = product.getSizes().split(",");
				for (String color : colors) {
					for (String size : sizes) {
						Sku sku = new Sku();
						sku.setProductId(product.getId());
						sku.setColorId(Long.parseLong(color));
						sku.setSize(size);
						sku.setMarketPrice(0f);
						sku.setPrice(0f);
						sku.setDeliveFee(0f);
						sku.setStock(0);
						sku.setUpperLimit(200);
						sku.setCreateTime(new Date());
						skuDao.insert(sku);
					}
				}
			}
		}
		return count > 0;
	}

	@Override
	public boolean isShow(Long[] ids) {
		Product product = new Product();
		product.setIsShow(true);
		for (final Long id : ids) {
			product.setId(id);
			productDao.updateByPrimaryKeySelective(product);
			// TODO add SOlr
			jmsTemplate.send(new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(String.valueOf(id));
				}
			});
			// TODO static
		}
		return false;
	}

}
