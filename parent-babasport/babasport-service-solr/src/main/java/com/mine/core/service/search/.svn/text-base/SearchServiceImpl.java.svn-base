package com.mine.core.service.search;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mine.core.bean.product.Product;
import com.mine.core.common.tools.PageValueUtil;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SolrServer solrServer;

	@Override
	public PageInfo<Product> selectProductPageByQuery(Integer pageNo, Integer pageSize, String keyword, Long brandId,
			String price) throws SolrServerException {

		Page<Product> products = new Page<>();
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "name_ik:" + keyword);
		// 过滤条件
		if (brandId != null) {
			solrQuery.addFilterQuery("brandId:" + brandId);
		}
		if (price != null) {
			String[] p = price.split("-");
			if (p.length == 2) {
				solrQuery.addFilterQuery("price:[" + p[0] + " TO " + p[1] + "]");
			} else {
				solrQuery.addFilterQuery("price:[" + p[0] + " TO *]");
			}

		}
		// 高亮
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("name_ik");
		// 样式 <span style='color:red'>2016</span>
		solrQuery.setHighlightSimplePre("<span style='color:red'>");
		solrQuery.setHighlightSimplePost("</span>");
		// 排序
		solrQuery.addSort("price", ORDER.asc);
		// 分页 limit 开始行 ， 每页显示条数
		solrQuery.setStart(PageValueUtil.startRow(pageNo, pageSize));
		solrQuery.setRows(PageValueUtil.checkPageSize(pageSize));
		// 执行查询
		QueryResponse response = solrServer.query(solrQuery);
		// 取高亮
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		// Map K : V 442 : Map
		// Map K : V name_ik : List<String>
		// List<String> list 2016年最新款191期卖的瑜伽服最新限量纯手工制作细心打造商户经典买一送一清
		// list.get(0);
		// 结果集
		SolrDocumentList docs = response.getResults();
		// 发现的条数 （总条件）构建分页时用到
		long numFound = docs.getNumFound();
		for (SolrDocument doc : docs) {
			// 创建商品对象
			Product product = new Product();
			// 商品ID
			String id = (String) doc.get("id");
			product.setId(Long.parseLong(id));
			// 商品名称 ik
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("name_ik");
			product.setName(list.get(0));
			/*
			 * String name = (String) doc.get("name_ik"); product.setName(name);
			 */
			// 图片
			String url = (String) doc.get("url");
			product.setImgUrl(url);// img,img2,img3
			// 价格 售价 select price from bbs_sku where product_id =442 order by
			// price asc limit 0,1
			Float minPrice = (Float) doc.get("price");
			product.setPrice(minPrice);
			// 品牌ID Long
			Integer sBrandId = (Integer) doc.get("brandId");
			product.setBrandId(Long.parseLong(String.valueOf(sBrandId)));

			products.add(product);
		}
		products.setPageNum(PageValueUtil.checkPageNumber(pageNo));
		products.setPageSize(PageValueUtil.checkPageSize(pageSize));
		products.setTotal(numFound);
		return new PageInfo<>(products);
	}

}
