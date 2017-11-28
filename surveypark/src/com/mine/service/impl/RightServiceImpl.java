package com.mine.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.dao.BaseDao;
import com.mine.model.Right;
import com.mine.service.RightService;
import com.mine.util.StringUtil;
import com.mine.util.ValidateUtil;

@Service
public class RightServiceImpl extends BaseServiceImpl<Right> implements
		RightService {

	@Autowired
	private BaseDao<Right> rightDao;

	/**
	 * 保存/更新 权限
	 * 
	 * @param model
	 */
	public void saveOrUpdateRight(Right model) {

		// id为空 则为添加 需要设置 pos code;
		if (model.getId() == null) {
			int pos = 0;
			long code = 1;
			String hql = "select max(r.rightPos),max(r.rightCode) from Right r where r.rightPos=(select max(rr.rightPos) from Right rr)";
			Object[] objs = (Object[]) rightDao.uniqueResult(hql);
			Integer currPos = (Integer) objs[0];
			Long currCode = (Long) objs[1];
			if (currPos != null) {
				if (currCode >= (1l << 60)) {
					pos++;
				} else {
					pos = currPos;
					code = currCode << 1l;
				}
			}
			model.setRightPos(pos);
			model.setRightCode(code);
		}
		rightDao.saveOrUpdateEntity(model);

	}

	/**
	 * 更具 url创建 right
	 * 
	 * @param url
	 */
	public void createRightByUrl(String url) {
		String hql = "select count(*) from Right r where r.rightUrl=?";
		long count = (Long) rightDao.uniqueResult(hql, url);
		if (count == 0) {
			Right r = new Right();
			r.setRightUrl(url);
			this.saveOrUpdateRight(r);
		}
	}

	/**
	 * 批量修改权限
	 * 
	 * @return
	 */
	public void batchUpdateRight(List<Right> rights) {
		String hql = "update Right r set r.rightName=?,r.common=? where r.id=?";
		for (Right r : rights) {
			rightDao.batchEntityByHql(hql, r.getRightName(),r.getCommon(),r.getId());
		}
	}

	/**
	 * 查询所有 包括rids的 right
	 * 
	 * @param rights
	 * @return
	 */
	public List<Right> findRigthsInRange(Integer[] rids) {
		if (ValidateUtil.isValidate(rids)) {
			String hql = "from Right r where r.id in("
					+ StringUtil.arr2str(rids) + ")";
			List<Right> rights = rightDao.findEntityByHql(hql);
			return rights;
		}
		return null;
	}

	/**
	 * 查询所有不 包括rids的 right
	 * 
	 * @param rights
	 * @return
	 */
	public List<Right> findRigthsNotInRange(Set<Right> rights) {
		if (!ValidateUtil.isValidate(rights)) {
			return this.findAllEntities();
		} else {
			String hql = "from Right r where r.id not in("
					+ changeSet2str(rights) + ")";
			List<Right> rightlist = rightDao.findEntityByHql(hql);
			return rightlist;
		}

	}

	private String changeSet2str(Set<Right> rights) {
		String str = "";
		if (ValidateUtil.isValidate(rights)) {
			for (Right r : rights) {
				str = str + r.getId() + ",";
			}
			return str.substring(0, str.length() - 1);
		}
		return str;
	}
}
