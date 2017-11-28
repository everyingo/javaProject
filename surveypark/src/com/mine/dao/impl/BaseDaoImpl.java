package com.mine.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.mine.dao.BaseDao;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T>{

	@Autowired
	private SessionFactory sf;
	
	public Class<T> clazz;
	public BaseDaoImpl(){
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class<T>) type.getActualTypeArguments()[0];
	}
	protected Session getCurrentSession() {
		return sf.getCurrentSession();
	}
	
	public void saveEntity(T entity) {
		getCurrentSession().save(entity);
	}

	public void saveOrUpdateEntity(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public void updateEntity(T entity) {
		getCurrentSession().update(entity);
	}

	public void deleteEntity(T entity) {
		getCurrentSession().delete(entity);
	}

	public void batchEntityByHql(String hql,Object...objs) {
		Query query= getCurrentSession().createQuery(hql);
		for (int i = 0; i < objs.length; i++) {
			query.setParameter(i, objs[i]);
		}
		query.executeUpdate();
	}
	
	public void executeSql(String sql,Object...objs){
		SQLQuery sqlquery= getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < objs.length; i++) {
			sqlquery.setParameter(i, objs[i]);
		}
		sqlquery.executeUpdate();
	}

	public T loadEntity(Serializable id) {
		
		return (T) getCurrentSession().load(clazz, id);
	}

	public T getEntity(Serializable id) {
		
		return (T) getCurrentSession().get(clazz, id);
	}

	public List<T> findEntityByHql(String hql,Object...objs) {
		Query query= getCurrentSession().createQuery(hql);
		for (int i = 0; i < objs.length; i++) {
			query.setParameter(i, objs[i]);
		}
		return query.list();
	}
	//µ¥Öµ¼ìËù
	public Object uniqueResult(String hql,Object...objs){
		Query query= getCurrentSession().createQuery(hql);
		for (int i = 0; i < objs.length; i++) {
			query.setParameter(i, objs[i]);
		}
		return query.uniqueResult();
	}
	public List<T> findPageByHql(String hql,Integer begin,Integer end,Object...objs){
		Query query= getCurrentSession().createQuery(hql);
		for (int i = 0; i < objs.length; i++) {
			query.setParameter(i, objs[i]);
		}
		return query.setFirstResult(begin).setMaxResults(end).list();
	}

	@SuppressWarnings("rawtypes")
	public List executeSqlQuery(Class<?> clazz,String sql,Object...objs){
		SQLQuery sqlquery= getCurrentSession().createSQLQuery(sql);
		if(clazz!=null){
			sqlquery.addEntity(clazz);
		}
		for (int i = 0; i < objs.length; i++) {
			sqlquery.setParameter(i, objs[i]);
		}
		return sqlquery.list();
	}
	
}
