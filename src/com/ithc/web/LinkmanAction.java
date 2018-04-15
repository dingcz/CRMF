package com.ithc.web;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ithc.bean.Linkman;
import com.ithc.service.LinkmanService;
import com.ithc.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
@SuppressWarnings("all")
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman>{
	
	/*linkman的所有jsp页面还没有改*/
	
	private Linkman linkman = new Linkman();
	@Override
	public Linkman getModel() {
		
		return linkman;
	}
	
	private LinkmanService linkmanService;
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}
	//跳转新增联系人
	public String initAddUI(){
		return "initAddUI";
	}
	//添加联系人
	public String save(){
		linkmanService.save(linkman);
		return initAddUI();
	}
	
	//当前页
	private Integer pageCode= 1;
	private Integer pageSize = 2;
	public void setPageCode(Integer pageCode) {
		if(pageCode == null){
			this.pageCode=1;
		}
		this.pageCode = pageCode;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			this.pageSize=2;
		}
		this.pageSize = pageSize;
	}
	//分页查询
	public String findByPage(){
		
		if(pageCode ==null){
			pageCode = 1;
		}
		if(pageSize == null){
			pageSize = 2;
		}
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		//筛选条件
		//拼接客户名称
		try{
			if(linkman != null){
				String lkm_name = linkman.getLkm_name();
				if(!lkm_name.equals("")&& lkm_name !=null ){
					criteria.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
				}
				
			}
		}catch(Exception e){
			
		}
		//拼接所属客户
		try{
			Long id = linkman.getCustomer().getCust_id();
			if(id != null){
				criteria.add(Restrictions.eq("customer.cust_id",id));
			}
			
		}catch(Exception e){
			
		}
		
		PageBean<Linkman> page = linkmanService.findByPage(pageCode,pageSize,criteria);
		List<Linkman> list = page.getBeanList();
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", page);
		
		return "findByPage";
		
	}
	
	//删除
	public String delete(){
		Long id = linkman.getLkm_id();
		if(id !=null){
			Linkman linkman =linkmanService.findById(id);
			if(linkman != null){
				linkmanService.delete(linkman);
				return findByPage();
			}
		}
		return "login";
	}
	
	//修改跳转
	public String initUpdae(){
		Long id = linkman.getLkm_id();
		if(id != null){
			linkman = linkmanService.findById(id);
		}
		return "initUpdate";
	}
	
	//修改保存
	public String update(){
		linkmanService.update(linkman);
		return "update";
	}
	

}
