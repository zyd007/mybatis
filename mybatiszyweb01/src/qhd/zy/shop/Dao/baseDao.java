package qhd.zy.shop.Dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import qhd.zy.shop.Util.DaoUtil;
import qhd.zy.shop.Util.SqlSessionUtil;
import qhd.zy.shop.model.Orders;
import qhd.zy.shop.model.Pager;
import qhd.zy.shop.model.SystemContext;


public class baseDao<T> {
	public baseDao() {
		DaoUtil.getDiDao(this);
	}
	public void add(Object obj){
		SqlSession session=null;
		try {
			session=SqlSessionUtil.getSession();
			session.insert(obj.getClass().getName()+".add", obj);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally{
			SqlSessionUtil.colse(session);
		}
	}
	public void delete(Class<T>cla,int id){
		SqlSession session=null;
		try {
			 session=SqlSessionUtil.getSession();
			 session.delete(cla.getName()+".delete", id);
			 //改变了数据库就要提交
			 session.commit();
		} finally{
			SqlSessionUtil.colse(session);
		}
	}
	public void update(Object obj){
		SqlSession session=null;
		try {
			session=SqlSessionUtil.getSession();
			session.update(obj.getClass().getName()+".update", obj);
			session.commit();
		} catch (Exception e) {
			//增加和更新回滚 以免没添加上
			session.rollback();
			e.printStackTrace();
		}finally{
			SqlSessionUtil.colse(session);
		}
	}
	@SuppressWarnings("unchecked")
	public T load(Class<T>cla,int id){
		SqlSession session=null;
		T t=null;
		try {
			 session=SqlSessionUtil.getSession();
			 t=(T)session.selectOne(cla.getName()+".load", id);
		} finally{
			SqlSessionUtil.colse(session);
		}
		return t;
	}
	//查一个属性
	@SuppressWarnings("unchecked")
	public T loadBySqlID(String sql,Object obj){
		SqlSession session=null;
			T t=null;
		try {
			 session=SqlSessionUtil.getSession();
			 t=(T)session.selectOne(sql, obj);
		} finally{
			SqlSessionUtil.colse(session);
		}
		return t;
	}
	//查多个
	@SuppressWarnings("unchecked")
	public T loadMore(String Sql,Map<String,Object>params){
		SqlSession session=null;
		T t=null;
	try {
		 session=SqlSessionUtil.getSession();
		 t=(T)session.selectOne(Sql,params);
	} finally{
		SqlSessionUtil.colse(session);
	}
	return t;
	}
	//查询不带分页的列表
	public List<T> listNotpage(Class<T>cla,Map<String,Object>params){
		SqlSession session=null;
		List<T>lists=new ArrayList<T>();
	try {
		 session=SqlSessionUtil.getSession();
		lists=session.selectList(cla.getName()+".list",params);
	} finally{
		SqlSessionUtil.colse(session);
	}
	return lists;
	}
	//查询带分页的列表
	public Pager<T> listPage(Class<T>cla,Map<String,Object>params){
		int pageRecord=SystemContext.getPageRecord();
		int offset=SystemContext.getOffset();
		String order=SystemContext.getOrder();
		Object sort=SystemContext.getSort();
		Pager<T>pages=new Pager<T>();
		if(params==null)
			params=new HashMap<String,Object>();
		SqlSession session=null;
		try {
			session=SqlSessionUtil.getSession();
			params.put("offset", offset);
			params.put("pageRecord", pageRecord);
			params.put("order", order);
			params.put("sort",sort);
			List<T>lists=session.selectList(cla.getName()+".find", params);
			pages.setDatas(lists);
			pages.setOffset(offset);
			pages.setPageRecord(pageRecord);
			int totalRecord=session.selectOne(cla.getName()+".findTotal", params);
			pages.setTotalRecord(totalRecord);
		} finally{
			SqlSessionUtil.colse(session);
		}
		
		return pages;
	}
	public void delete(String SqlId, Map<String, Object> params) {
		SqlSession session=null;
		try {
			 session=SqlSessionUtil.getSession();
			 session.delete(SqlId, params);
			 //改变了数据库就要提交
			 session.commit();
		} finally{
			SqlSessionUtil.colse(session);
		}
	}
	public Pager<T> listPage(String SqlId, Map<String, Object> params) {
		int pageRecord=SystemContext.getPageRecord();
		int offset=SystemContext.getOffset();
		String order=SystemContext.getOrder();
		Object sort=SystemContext.getSort();
		Pager<T>pages=new Pager<T>();
		if(params==null)
			params=new HashMap<String,Object>();
		SqlSession session=null;
		try {
			session=SqlSessionUtil.getSession();
			params.put("offset", offset);
			params.put("pageRecord", pageRecord);
			params.put("order", order);
			params.put("sort",sort);
			List<T>lists=session.selectList(SqlId, params);
			pages.setDatas(lists);
			pages.setOffset(offset);
			pages.setPageRecord(pageRecord);
			int totalRecord=session.selectOne(SqlId+"_count", params);
			pages.setTotalRecord(totalRecord);
		} finally{
			SqlSessionUtil.colse(session);
		}
		return pages;
	}
}
