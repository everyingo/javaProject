package com.mine.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {

	    //写操作
		public void saveEntity(T entity);
		public void saveOrUpdateEntity(T entity);
		public void updateEntity(T entity);
		public void deleteEntity(T entity);
		public void batchEntityByHql(String hql,Object...objs);
		public void executeSql(String sql,Object...objs);
		
		//读操作
		public T loadEntity(Serializable id);
		public T getEntity(Serializable id);
		public List<T> findEntityByHql(String hql,Object...objs);
		public List<T> findPageByHql(String hql,Integer begin,Integer end,Object...objs);
		
		//单值检所  确保结果 有且只有一个值
		public Object uniqueResult(String hql,Object...objs);
		public List<T> findAllEntities();
		
		@SuppressWarnings("rawtypes")
		public List executeSqlQuery(Class<?> clazz,String sql,Object...objs);
}
