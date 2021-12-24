package com.zioer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/group")
public class GroupController {
    
    @Autowired
	protected IdentityService identityService;
    
    @RequestMapping(value = "/list")
    public String list(Model model) {
    	List<Group> datas=identityService.createGroupQuery().list();    	
    	model.addAttribute("groups", datas);    	
    	return "group_list";
    }
    
    @RequestMapping(value = "/pagelist")
    public String pagelist(Model model,HttpServletRequest request) {
    	long pageSize = 10;
    	long page = 0 ;
    	long totalPage = 0;
    	long totalRows = 0;
    	
    	long firstResult;	//查询的起始记录数
    	long maxResults = pageSize; //查询的每页显示记录数
    	
    	totalRows = identityService.createGroupQuery().count();
    	if ( totalRows % pageSize == 0){
    		totalPage = totalRows / pageSize;
    	}else{
    		totalPage = totalRows / pageSize + 1;
    	}
    	
    	if (request.getParameter("page") == null){
    		page = 1;
    	}else{
    		page = Integer.parseInt(request.getParameter("page"));
    		if (page<1) {
    			page = 1;
    		}
    		else if (page > totalPage) {
    			page = totalPage;
    		}
    	}
    	
    	firstResult = pageSize * (page - 1);
    	
    	List<Group> datas=identityService.createGroupQuery().listPage( (int)firstResult, (int)maxResults );
    	
    	model.addAttribute("groups", datas);
    	model.addAttribute("currentPage", page);
    	model.addAttribute("totalPage", totalPage);
    	
    	
    	return "group_list2";
    }
    
    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable String id){
    	ModelAndView mv = new ModelAndView();
    	Group data = identityService.createGroupQuery().groupId(id).singleResult();
    	List<User> userList = identityService.createUserQuery()  
    			.memberOfGroup(id).list();  
    	
        mv.setViewName("group_view");
    	mv.addObject("group", data);
    	mv.addObject("userList", userList);
    	return mv;
    }  
    
}
