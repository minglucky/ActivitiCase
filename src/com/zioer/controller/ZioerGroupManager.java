package com.zioer.controller;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.zioer.model.Zzdepartment;
import com.zioer.model.Zzrole;
import com.zioer.model.Zzuser;
import com.zioer.service.ZzdepartmentService;
import com.zioer.service.ZzroleService;
import com.zioer.service.ZzuserService;

public class ZioerGroupManager extends GroupEntityManager{
	@Autowired
    private ZzroleService zzroleService;
	@Autowired
    private ZzdepartmentService zzdepartmentService;
	@Autowired
    private ZzuserService zzuserService;
	
    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
    	System.out.println("***************");
		System.out.println("findGroupByQueryCriteria");
		
		List<Group> groupList=new ArrayList<Group>();
    	GroupEntity  group = new GroupEntity ();
    	String userId = query.getUserId();
    	String groupId = query.getId();
    	String departmentId;
    	String roleId;
    	
		if (groupId != null){
			departmentId = groupId.split("\\:")[0].toString();
			roleId = groupId.split("\\:")[1].toString();

			Zzdepartment tempDepartment = zzdepartmentService.selectByPrimaryKey(departmentId);
			Zzrole tempRole = zzroleService.selectByPrimaryKey(roleId);
			
			if (tempRole != null && tempDepartment != null){
				group.setId(tempDepartment.getDepartment_id() + ":" + tempRole.getRole_id());
				group.setName(tempDepartment.getDepartmentname() + ":" + tempRole.getRolename());
				groupList.add(group);
			}
		}else if (userId != null){
			Zzuser tempUser = zzuserService.selectByPrimaryKey(userId);
			departmentId = tempUser.getDepartment_id();
			roleId = tempUser.getRole_id();

			Zzdepartment tempDepartment = zzdepartmentService.selectByPrimaryKey(departmentId);
			Zzrole tempRole = zzroleService.selectByPrimaryKey(roleId);
			
			if (tempRole != null && tempDepartment != null){
				group.setId(tempDepartment.getDepartment_id() + ":" + tempRole.getRole_id());
				group.setName(tempDepartment.getDepartmentname() + ":" + tempRole.getRolename());
				groupList.add(group);
			}			
		}else{
			List<Zzdepartment> tempDepartments = zzdepartmentService.listAll(null);
			List<Zzrole> tempRoles = zzroleService.listAll(null);
			
			for(Zzdepartment tempDepartment : tempDepartments ){
				for(Zzrole tempRole : tempRoles ){
					group = new GroupEntity ();
					group.setId(tempDepartment.getDepartment_id() + ":" + tempRole.getRole_id());
					group.setName(tempDepartment.getDepartmentname() + ":" + tempRole.getRolename());
					groupList.add(group);
				}
		    }
			
		}
		
		return groupList;
    }

}
