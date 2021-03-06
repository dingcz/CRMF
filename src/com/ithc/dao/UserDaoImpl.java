package com.ithc.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ithc.bean.User;
@SuppressWarnings("all")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	//登入
	public User login(User user, String state) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ? and user_password = ? and user_state = ?"
				, user.getUser_code(),user.getUser_password(),state);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	//ajax注册登入名时判断
	public User registerName(String user_code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ?",user_code);
		if(list != null && list.size() >0){
			//System.out.println("list.get(0)=="+list.get(0));
			
			return list.get(0);
		}
		
		return null;
	}
	//注册
	@Override
	public void register(User user) {
		this.getHibernateTemplate().save(user);
		
	}

	@Override
	public User findByName(String user_code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ?", user_code);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	//校验密码
	@Override
	public User checkPassword(String user_code, String newuser_password) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code =? and user_password=?", user_code,newuser_password);
		if(list !=null&& list.size()>0){
			return list.get(0);
		}
		return null;
	}
	//登入密码
	@Override
	public void password(User user) {
		this.getHibernateTemplate().update(user);
		
	}

	@Override
	public List<User> findByAll() {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User");
		if(list !=null && list.size()>0 ){
			return list;
		}
		return null;
	}

}
