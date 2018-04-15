package com.ithc.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
@SuppressWarnings("all")
public class UtilDaoImpl<T> extends HibernateDaoSupport implements UtilDao<T> {
	
	Class clazz;
	public UtilDaoImpl(){
		//this代表子类，子类加载的时候父类也会加载
		Type type = this.getClass().getGenericSuperclass();
		
		if( type instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type[] types = parameterizedType.getActualTypeArguments();
			clazz = (Class) types[0];
		}
	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
		
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
		
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}

	@Override
	public T findById(Long id) {
		T t = (T) this.getHibernateTemplate().get(clazz, id);
		if(t != null){
			return t;
		}
		return null;
	}

	@Override
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		PageBean<T> pageBean = new PageBean<T>();
		//设置当前页
		pageBean.setPageCode(pageCode);
		//设置每页显示的数量
		pageBean.setPageSize(pageSize);
		//设置总记录数
		List<Number> totalCount = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria.setProjection(Projections.rowCount()));
		if(totalCount != null&& totalCount.size()>0){
			pageBean.setTotalCount(totalCount.get(0).intValue());
		}
		criteria.setProjection(null);
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria,(pageCode-1)*pageSize,pageSize);
		if(list !=null&& list.size()>0){
			pageBean.setBeanList(list);
			return pageBean;
		}
		return null;
	}

}
