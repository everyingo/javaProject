package com.mine.core.service.product;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mine.core.bean.product.Brand;
import com.mine.core.bean.product.BrandQuery;
import com.mine.core.common.tools.PageValueUtil;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.dao.product.BrandDao;
import com.mine.core.redis.product.BrandRedis;
import com.mine.core.service.product.BrandService;

@Service("brandService")
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private BrandRedis brandRedis;

	/**
	 * 分页查询品牌
	 */
	@Override
	public PageInfo<Brand> selectPageByQuery(Integer pageNo, Integer pageSize, String name, Boolean isDisplay) {
		PageHelper.startPage(PageValueUtil.checkPageNumber(pageNo), PageValueUtil.checkPageSize(pageSize));
		BrandQuery brandQuery = new BrandQuery();
		BrandQuery.Criteria criteria = brandQuery.createCriteria();
		if (ValidateUtil.isValidate(name)) {
			criteria.andNameLike("%" + name + "%");
		}
		if (isDisplay == null) {
			criteria.andIsDisplayEqualTo(false);
		} else {
			criteria.andIsDisplayEqualTo(isDisplay);
		}
		return new PageInfo<Brand>(brandDao.selectByExample(brandQuery));
	}

	/**
	 * 查询品牌ById
	 */
	@Override
	public Brand selectBrandById(Long id) {
		return brandDao.selectByPrimaryKey(id);
	}

	/**
	 * 查询所有品牌
	 */
	@Override
	public List<Brand> selectBrandByIsDispaly(Boolean isDisplay) {
		BrandQuery brandQuery = new BrandQuery();
		brandQuery.createCriteria().andIsDisplayEqualTo(isDisplay);
		return brandDao.selectByExample(brandQuery);
	}

	/**
	 * 批量删除
	 */
	@Override
	public int deletes(Long[] ids) {
		if (ValidateUtil.isValidate(ids)) {
			BrandQuery brandQuery = new BrandQuery();
			BrandQuery.Criteria criteria = brandQuery.createCriteria();
			criteria.andIdIn(Arrays.asList(ids));
			brandRedis.deleteBrands2Redis(ids);
			return brandDao.deleteByExample(brandQuery);
		}
		return 0;
	}

	/**
	 * 删除
	 */
	@Override
	public int delete(Long id) {
		brandRedis.deleteBrand2Redis(id);
		return brandDao.deleteByPrimaryKey(id);
	}

	/**
	 * 更新
	 */
	@Override
	public int updateBrandById(Brand brand) {
		brandRedis.insertBrand2Redis(brand.getId(), brand.getName());
		return brandDao.updateByPrimaryKeySelective(brand);
	}

	@Override
	public List<Brand> selectBrandListFromRedis() {
		return brandRedis.selectBrandListFromRedis();
	}

}
