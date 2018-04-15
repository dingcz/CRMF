package com.ithc.dao;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.Customer;
import com.ithc.util.PageBean;

public interface CustomerDao {
	//新增加
	void save(Customer customer);
	//	分页去查询
	PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

}
