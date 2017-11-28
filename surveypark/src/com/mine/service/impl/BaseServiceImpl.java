package com.mine.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mine.dao.BaseDao;
import com.mine.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl(){
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class<T>) type.getActualTypeArguments()[0];
	}
	@Autowired
	private BaseDao<T> baseDao;

	public void saveEntity(T entity) {
		baseDao.saveEntity(entity);
	}

	public void saveOrUpdateEntity(T entity) {
		baseDao.saveOrUpdateEntity(entity);
	}

	public void updateEntity(T entity) {
		baseDao.updateEntity(entity);
	}

	public void deleteEntity(T entity) {
		baseDao.deleteEntity(entity);
	}

	public void batchEntityByHql(String hql, Object... objs) {
		baseDao.batchEntityByHql(hql, objs);
	}
	public void executeSql(String sql,Object...objs){
		baseDao.executeSql(sql, objs);
	}

	public T loadEntity(Serializable id) {

		return baseDao.loadEntity(id);
	}

	public T getEntity(Serializable id) {

		return baseDao.getEntity(id);
	}

	public List<T> findEntityByHql(String hql, Object... objs) {

		return baseDao.findEntityByHql(hql, objs);
	}

	public List<T> findPageByHql(String hql, Integer begin, Integer end,
			Object... objs) {
		return baseDao.findPageByHql(hql, begin, end, objs);
	}

	// 单值检所 确保结果 有且只有一个值
	public Object uniqueResult(String hql, Object... objs) {
		return baseDao.uniqueResult(hql, objs);
	}
	
	public List<T> findAllEntities(){
		String hql=" from "+clazz.getName();
		return baseDao.findEntityByHql(hql);
	}
	
	@SuppressWarnings("rawtypes")
	public List executeSqlQuery(Class<?> clazz,String sql,Object...objs){
		return baseDao.executeSqlQuery(clazz, sql, objs);
	}

}
