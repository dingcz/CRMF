package com.ithc.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.Customer;
import com.ithc.dao.CustomerDao;
import com.ithc.util.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService{
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	//新增加
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
		
	}
	//分页去查询
	@Override
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		return customerDao.findByPage(pageCode,pageSize,criteria);
	}
	
}
