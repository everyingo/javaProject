package com.mine.core.service.search;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.mine.core.bean.product.Product;
import com.mine.core.bean.product.Sku;
import com.mine.core.bean.product.SkuQuery;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.dao.product.ProductDao;
import com.mine.core.dao.product.SkuDao;
import com.mine.core.service.search.InsertService;

@Service("insertServiceImpl")
public class InsertServiceImpl implements InsertService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private SkuDao skuDao;

	@Autowired
	private SolrServer solrServer;

	public void insertProduct2Solr(Long productId) {
		Product p = productDao.selectByPrimaryKey(productId);
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", p.getId());
		doc.setField("name_ik", p.getName());
		doc.setField("url", p.getImages() != null ? p.getImages()[0] : null);
		// sku
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setFields("price");
		skuQuery.setOrderByClause("price asc");
		skuQuery.createCriteria().andProductIdEqualTo(p.getId());
		PageHelper.startPage(1, 1);
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		float price = ValidateUtil.isValidate(skus) ? skus.get(0).getPrice() : 0f;
		doc.setField("price", price);
		doc.setField("brandId", p.getBrandId());
		try {
			solrServer.add(doc);
			solrServer.commit();
		} catch (Exception e) {
		}
	}
}
