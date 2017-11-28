package com.mine.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Right;
import com.mine.service.RightService;

/**
 * Ȩ��action
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
	 * Ȩ���б�
	 * @return
	 */
	public String toRightsListPage(){
	    this.rights=rightService.findAllEntities();
		return "rightsListPage";
	}
	/**
	 * �������Ȩ��ҳ��
	 * @return
	 */
	public String toAddRightPage(){
		
		return "toAddRightPage";
	}
	/**
	 * �༭Ȩ��
	 * @return
	 */
	public String toEditRightPage(){
		this.model=rightService.getEntity(rid);
		return "toEditRightPage";
	}
	
	/**
	 * ����/���� Ȩ��
	 * @return
	 */
	public String saveOrUpdateRight(){
		rightService.saveOrUpdateRight(model);
		return "toRightListAction";
	}
	
	/**
	 * ɾ��Right
	 * @return
	 */
	public String deleteRight(){
		Right right=new Right();
		right.setId(rid);
		rightService.deleteEntity(right);
		return "toRightListAction";
	}
	/**
	 * �����޸�Ȩ��
	 * @return
	 */
	public String batchUpdateRight(){
		rightService.batchUpdateRight(rights);
		return "toRightListAction";
	}
}
