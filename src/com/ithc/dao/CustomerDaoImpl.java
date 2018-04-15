package com.ithc.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ithc.bean.Customer;
import com.ithc.util.PageBean;
@SuppressWarnings("all")
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	@Override
	public void save(Customer customer) {
	 this.getHibernateTemplate().save(customer);
		
		
	}

	@Override
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		PageBean<Customer> pageBean = new PageBean<Customer>(); 
		//设置当前页
		pageBean.setPageCode(pageCode);
		//设置每页显示的数量去球总页数
		pageBean.setPageSize(pageSize);
		//select count(*)
		List<Number> number = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria.setProjection(Projections.rowCount()));
		if(number != null && number.size()>0){
			int intValue = number.get(0).intValue();
			pageBean.setTotalCount(intValue);
			
		}
		//查询控制
		criteria.setProjection(null);
		
		//设置beanList集合
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria,(pageCode-1)*pageSize,pageSize);
		
		if(list !=null && list.size()>0){
			
			pageBean.setBeanList(list);
		}
		
		
		return pageBean;
	}

}





















