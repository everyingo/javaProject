package com.mine.core.dao.product;

import com.mine.core.bean.product.Sku;
import com.mine.core.bean.product.SkuQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuDao {
	int countByExample(SkuQuery example);

	int deleteByExample(SkuQuery example);

	int deleteByPrimaryKey(Long id);

	int insert(Sku record);

	int insertSelective(Sku record);

	List<Sku> selectByExample(SkuQuery example);

	Sku selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") Sku record, @Param("example") SkuQuery example);

	int updateByExample(@Param("record") Sku record, @Param("example") SkuQuery example);

	int updateByPrimaryKeySelective(Sku record);

	int updateByPrimaryKey(Sku record);

	int updateStockByPrimaryKey(@Param("skuId") Long skuId, @Param("ammount") Integer ammount);
}