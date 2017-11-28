package com.mine.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Right;
import com.mine.service.RightService;

/**
 * 权限action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right>{

	
	private static final long serialVersionUID = -7689330384059135328L;
	
	
	@Resource(name="rightServiceImpl")
	private RightService rightService;
	
	private List<Right> rights=new ArrayList<Right>();
	
    private Integer rid;
    
	public List<Right> getRights() {
		return rights;
	}
	public void setRights(List<Right> rights) {
		this.rights = rights;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}


	/**
	 * 权限列表
	 * @return
	 */
	public String toRightsListPage(){
	    this.rights=rightService.findAllEntities();
		return "rightsListPage";
	}
	/**
	 * 到达添加权限页面
	 * @return
	 */
	public String toAddRightPage(){
		
		return "toAddRightPage";
	}
	/**
	 * 编辑权限
	 * @return
	 */
	public String toEditRightPage(){
		this.model=rightService.getEntity(rid);
		return "toEditRightPage";
	}
	
	/**
	 * 保存/更新 权限
	 * @return
	 */
	public String saveOrUpdateRight(){
		rightService.saveOrUpdateRight(model);
		return "toRightListAction";
	}
	
	/**
	 * 删除Right
	 * @return
	 */
	public String deleteRight(){
		Right right=new Right();
		right.setId(rid);
		rightService.deleteEntity(right);
		return "toRightListAction";
	}
	/**
	 * 批量修改权限
	 * @return
	 */
	public String batchUpdateRight(){
		rightService.batchUpdateRight(rights);
		return "toRightListAction";
	}
}
