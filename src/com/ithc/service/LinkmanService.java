package com.ithc.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.Linkman;
import com.ithc.util.PageBean;

@SuppressWarnings("all")
public interface LinkmanService {
	//添加联系人
	void save(Linkman linkman);

	Linkman findById(Long id);

	void delete(Linkman linkman);

	void update(Linkman linkman);

	PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

}
