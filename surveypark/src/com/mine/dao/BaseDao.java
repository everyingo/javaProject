package com.mine.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

	//д����
	public void saveEntity(T entity);
	public void saveOrUpdateEntity(T entity);
	public void updateEntity(T entity);
	public void deleteEntity(T entity);
	public void batchEntityByHql(String hql,Object...objs);
	public void executeSql(String sql,Object...objs);
	
	//������
	public T loadEntity(Serializable id);
	public T getEntity(Serializable id);
	public List<T> findEntityByHql(String hql,Object...objs);
	public List<T> findPageByHql(String hql,Integer begin,Integer end,Object...objs);
	
	//��ֵ����  ȷ����� ����ֻ��һ��ֵ
	public Object uniqueResult(String hql,Object...objs);
	
	//
	@SuppressWarnings("rawtypes")
	public List executeSqlQuery(Class<?> clazz,String sql,Object...objs);
}
