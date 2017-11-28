package com.mine.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.dao.BaseDao;
import com.mine.model.Log;
import com.mine.service.LogService;
import com.mine.util.LogUtil;
import com.mine.util.StringUtil;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService{

	
	@Autowired
	private BaseDao<Log> logDao;
	
	
	
	@Override
	public void saveEntity(Log l) {
		String tableName=LogUtil.createLogTableName(0);
		String sql="insert into "+tableName+" (id,operator,oper_name,oper_params,oper_result,result_msg,oper_time) values(?,?,?,?,?,?,?)";
		String id=StringUtil.getUUID();
		logDao.executeSql(sql,id,l.getOperator(),l.getOperName(),l.getOperParams(),l.getOperResult(),l.getResultMsg(),l.getOperTime());
	}
	/**
	 * 按照创建时间升序排序查询日志
	 * @return
	 */
	public List<Log> findLogList(){
		String hql="from Log order by operTime";
		return logDao.findEntityByHql(hql);
	}
	/**
	 * 创建日志表
	 */
	public void createLogTable(String tableName) {
		String sql="create table if not exists "+ tableName +" like logs";
		logDao.executeSql(sql);
	}
	/**
	 * 查询近几个月的日志
	 * @param n
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Log> findNearLogList(Integer n){
		String tableName=LogUtil.createLogTableName(0);
		//查询出近几个月的日志表名称
		String sql=" select table_name from information_schema.tables" +
				   " where table_schema='surveypark' " +
				   " and table_name like 'logs_%' " +
				   " and table_name <= '"+tableName+"' " +
				   " order by table_name desc limit 0,"+n;
		List tabs=logDao.executeSqlQuery(null, sql);
		sql="";
		for(int i=0;i<tabs.size();i++){
			
			if(i==(tabs.size()-1)){
				sql+=" select * from "+tabs.get(i);
			}else{
				sql+=" select * from "+tabs.get(i) +" union ";
			}
		}
		List<Log> logs=logDao.executeSqlQuery(Log.class, sql);
		return logs;
	}

}
