package com.mine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mine.core.bean.TestTb;
import com.mine.core.bean.product.Product;
import com.mine.core.service.TestTbService;
import com.mine.core.service.product.ProductService;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class TestTbTest {

	@Autowired
	private TestTbService testTbService;

	@Autowired
	private Jedis Jedis;

	@Autowired
	private ProductService productService;

	@Autowired
	private SolrServer solrServer;

	@Test
	public void testAdd() {

		TestTb tt = new TestTb();
		tt.setName("w5");
		tt.setBirthday(new Date());

		testTbService.insertTestTb(tt);

	}

	@Test
	public void testBrandQuery() {

	}

	@Test
	public void testRedis() {
		long pno = Jedis.incr("pno");
		System.out.println(pno);
	}

	@Test
	public void testSolr() throws SolrServerException, IOException {
		String baseUrl = "http://search.everyingo.club:8001/solr";
		SolrServer solerServer = new HttpSolrServer(baseUrl);
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", "3");
		doc.setField("name", "星爷");
		solerServer.add(doc);
		solerServer.commit();
	}

	@Test
	public void testAddSolr() {
		boolean flag = productService.isShow(
				new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L,
						21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L, 29L, 30L, 31L, 32L, 33L, 34L, 35L, 36L, 37L });
		System.out.println(flag);
	}

	@Test
	public void testSelectSolr() throws SolrServerException {
		String keyword = "最新款";
		List<Product> products = new ArrayList<>();
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "name_ik:" + keyword);
		// 过滤条件
		// 高亮
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("name_ik");
		// 样式 <span style='color:red'>2016</span>
		solrQuery.setHighlightSimplePre("<span style='color:red'>");
		solrQuery.setHighlightSimplePost("</span>");
		// 排序
		solrQuery.addSort("price", ORDER.asc);
		// 分页 limit 开始行 ， 每页显示条数
		solrQuery.setStart(0);
		solrQuery.setRows(12);
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
		// long numFound = docs.getNumFound();
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
			Float price = (Float) doc.get("price");
			product.setPrice(price);
			// 品牌ID Long
			Integer brandId = (Integer) doc.get("brandId");
			product.setBrandId(Long.parseLong(String.valueOf(brandId)));

			products.add(product);
		}
		System.out.println(products.size());
	}
}
