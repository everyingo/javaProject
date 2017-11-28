package com.mine.core.service.product;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.mine.core.bean.product.Brand;

public interface BrandService {

	/**
	 * 查询所有品牌
	 * 
	 * @return
	 */
	List<Brand> selectBrandByIsDispaly(Boolean isDisplay);

	/**
	 * 查询所有品牌from redis
	 * 
	 * @return
	 */
	List<Brand> selectBrandListFromRedis();

	/**
	 * 分页查询品牌ByQuery
	 * 
	 * @param brandQuery
	 * @return
	 */
	PageInfo<Brand> selectPageByQuery(Integer pageNo, Integer pageSize, String name, Boolean isDisplay);

	/**
	 * 查询品牌ById
	 * 
	 * @param id
	 * @return
	 */
	Brand selectBrandById(Long id);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	int deletes(Long[] ids);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	int delete(Long id);

	/**
	 * 更新
	 * 
	 * @param brand
	 * @return
	 */
	int updateBrandById(Brand brand);
}
