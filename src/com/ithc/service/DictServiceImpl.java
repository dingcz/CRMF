package com.ithc.service;

import java.util.List;

import com.ithc.bean.Dict;
import com.ithc.dao.DictDao;
//查询客户级别与信息来源
public class DictServiceImpl implements DictService{
	
	private DictDao dictDao;

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}


	@Override
	public List<Dict> findByCode(String code) {
		
		return dictDao.findByCode(code);
	}

}
