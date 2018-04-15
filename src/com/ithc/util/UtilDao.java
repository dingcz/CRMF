package com.ithc.util;

import org.hibernate.criterion.DetachedCriteria;

public interface UtilDao<T> {
	//增
	void save(T t);
	//删除
	void delete(T t);
	//改
	void update(T t);
	//查询单个
	T findById(Long id);
	//分页查询
	PageBean<T> findByPage(Integer pageCode,Integer pageSize,DetachedCriteria criteria);
}
