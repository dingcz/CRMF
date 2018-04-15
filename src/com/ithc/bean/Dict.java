package com.ithc.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class Dict implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//数据字典主键
	private String dict_id;
	//数据字典类别代码
	private String dict_type_code;
	//数据字典类别名称
	private String dict_type_name;
	//数据字典项目名称
	private String dict_item_name;
	//数据字典项目可为空
	private String dict_item_code;
	//排序字段
	private String dict_sort;
	//1：使用 0：停用
	private String dict_enable;
	//备注
	private String dict_memo;
	
	//配置客户多方
	@JSONField(serialize=false)
	private Set<Customer> customers =    new HashSet<Customer>();

	public String getDict_id() {
		return dict_id;
	}

	public void setDict_id(String dict_id) {
		this.dict_id = dict_id;
	}

	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	public String getDict_type_name() {
		return dict_type_name;
	}

	public void setDict_type_name(String dict_type_name) {
		this.dict_type_name = dict_type_name;
	}

	public String getDict_item_name() {
		return dict_item_name;
	}

	public void setDict_item_name(String dict_item_name) {
		this.dict_item_name = dict_item_name;
	}

	public String getDict_item_code() {
		return dict_item_code;
	}

	public void setDict_item_code(String dict_item_code) {
		this.dict_item_code = dict_item_code;
	}

	public String getDict_sort() {
		return dict_sort;
	}

	public void setDict_sort(String dict_sort) {
		this.dict_sort = dict_sort;
	}

	public String getDict_enable() {
		return dict_enable;
	}

	public void setDict_enable(String dict_enable) {
		this.dict_enable = dict_enable;
	}

	public String getDict_memo() {
		return dict_memo;
	}

	public void setDict_memo(String dict_memo) {
		this.dict_memo = dict_memo;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Dict [dict_id=" + dict_id + ", dict_type_code=" + dict_type_code + ", dict_type_name=" + dict_type_name
				+ ", dict_item_name=" + dict_item_name + ", dict_item_code=" + dict_item_code + ", dict_sort="
				+ dict_sort + ", dict_enable=" + dict_enable + ", dict_memo=" + dict_memo + "]";
	}
	
	public Dict() {
	}
	
	
	
}