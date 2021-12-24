package com.zioer.controller;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.zioer.model.Zzuser;
import com.zioer.service.ZzuserService;

public class ZioerUserManager extends UserEntityManager{
	@Autowired
    private ZzuserService zzuserService;
    
	@Override
	public User findUserById(String userId) {
		UserEntity  user = new UserEntity ();
		Zzuser tempUser = zzuserService.selectByPrimaryKey(userId);
		if (tempUser != null){
			user.setId(tempUser.getUser_id());
			user.setFirstName(tempUser.getUsername());
			user.setPassword(tempUser.getPsd());
		}
		return user;
	}
	
	@Override
    public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		System.out.println("*************");
		System.out.println("findUserByQueryCriteria");
		
		List<User> userList=new ArrayList<User>();
		UserEntity user = new UserEntity ();
		Zzuser sql_param = new Zzuser();
		int first = query.getFirstResult();
		int max = query.getMaxResults();
		String userId = query.getId();
		String groupId = query.getGroupId();
		
		sql_param.setUser_id(userId);
		
		if (groupId != null){
			String departmentId = groupId.split("\\:")[0].toString();
			String roleId = groupId.split("\\:")[1].toString();
			
			sql_param.setDepartment_id(departmentId);
			sql_param.setRole_id(roleId);
			
		}
		
		sql_param.setFirstResult(first);
		sql_param.setMaxResults(max);
		
		List<Zzuser> tempUsers = zzuserService.listAll(sql_param);
		for (Zzuser tempUser : tempUsers) {
			user = new UserEntity ();
			user.setId(tempUser.getUser_id());
			user.setFirstName(tempUser.getUsername());
			user.setPassword(tempUser.getPsd());
			userList.add(user);
		}
		
		return userList;
    }
	
	@Override
	public Boolean checkPassword(String userId, String password){
		System.out.println("*************");
		System.out.println("checkPassword");

		Boolean ret = false;
		Zzuser tempUser = zzuserService.selectByPrimaryKey(userId);
		
		if (tempUser != null && tempUser.getPsd().equals(password)){
			ret = true;
		}
		return ret;
	}

}
