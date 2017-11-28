package com.mine.core.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.mine.core.bean.product.Brand;
import com.mine.core.service.product.BrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;

	/**
	 * 品牌列表
	 * 
	 * @param data
	 * @param brandQuery
	 * @return
	 */
	@RequestMapping("/list.do")
	public String list(Map<String, Object> data, Integer pageNo, Integer pageSize, String name, Boolean isDisplay) {
		StringBuffer params = new StringBuffer("/brand/list.do?");
		PageInfo<Brand> page = brandService.selectPageByQuery(pageNo, pageSize, name, isDisplay);
		if (name != null)
			params.append("name=" + name);
		if (isDisplay != null)
			params.append("&isDisplay=" + isDisplay);
		else
			params.append("&isDisplay=" + false);

		data.put("page", page);
		data.put("pageUrl", params.toString());
		data.put("name", name);
		data.put("isDisplay", isDisplay);
		return "brand/list";
	}

	/**
	 * 去编辑
	 * 
	 * @param data
	 * @param id
	 * @return
	 */
	@RequestMapping("/toEdit.do")
	public String toEdit(Map<String, Object> data, Long id) {
		Brand brand = brandService.selectBrandById(id);
		data.put("brand", brand);
		return "brand/edit";
	}

	/**
	 * 更新
	 * 
	 * @param brand
	 * @return
	 */
	@RequestMapping("/edit.do")
	public String edit(Brand brand) {
		brandService.updateBrandById(brand);
		return "redirect:/brand/list.do";
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deletes.do")
	public String deletes(Long[] ids) {
		brandService.deletes(ids);
		return "forward:/brand/list.do";
	}

	/**
	 * 单一删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete.do")
	public String deletes(Long id) {
		brandService.delete(id);
		return "forward:/brand/list.do";
	}

}
