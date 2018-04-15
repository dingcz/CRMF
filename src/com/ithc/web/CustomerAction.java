package com.ithc.web;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ithc.bean.Customer;
import com.ithc.service.CustomerService;
import com.ithc.util.PageBean;
import com.ithc.util.UUIDNAME;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	private Customer customer = new Customer();
	
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Customer getModel() {
		
		return customer;
	}
	
	//点击新增客户跳转
	public String initAddUI(){
		return "initAddUI";
	}
	
	//文件上传
	//文件
	private File upload;
	//文件名
	private String uploadFileName;
	//文件类型
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	public String save() throws Exception{
		
		if(upload != null){
			//名字唯一
			String uuidname = UUIDNAME.getUUIDNAME(uploadFileName);
			String files = "F:/file/"+uuidname;
			File file = new File(files);
			FileUtils.copyFile(upload, file);
			customer.setFilePath(files);
					
		}
		customerService.save(customer);
		
		return "initAddUI";	
	}
	
	
	//分页查询
	//利用属性驱动来获取当前页
	private Integer pageCode = 1;

	public void setPageCode(Integer pageCode) {
		if(pageCode == null){
			pageCode = 1;
		}
		if(pageCode < 1){
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	
	private Integer pageSize = 2;

	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize = 2;
		}
		this.pageSize = pageSize;
	}
	
	
	public String findByPage(){
		
		//使用离线条件查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if(customer != null){
			try{
				//拼接客户名称
				String cust_name =customer.getCust_name();
				if(cust_name.equals("")){
					criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
				}
			}catch(Exception e){
				
			}
			
			try{
				//拼接客户级别
				String id = customer.getLevel().getDict_id();
				System.out.println(id);
				if(!id.equals("")){
					criteria.add(Restrictions.eq("level.dict_id", id));
				}
			}catch(Exception e){
				
			}
			
			try{
				//拼接客户来源
				String id= customer.getSource().getDict_id();
				System.out.println(id);
				if(!id.equals("")){
					criteria.add(Restrictions.eq("source.dict_id", id));
				}
			}catch(Exception e){
				
			}
			
		}
		PageBean<Customer> page =   customerService.findByPage(pageCode,pageSize,criteria);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", page);
		
		
		return "findByPage";
		
	}
	
	
	
	
	
	
	
	
	
	

}
