package com.zioer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zioer.model.Zzdepartment;
import com.zioer.service.ZzdepartmentService;

@Controller
@RequestMapping(value = "/department")
public class ZzdepartmentController {
    
	@Autowired
	protected IdentityService identityService;
	@Autowired
	protected ZzdepartmentService zzdepartmentService;
    
	@RequestMapping(value = "/add")
    public ModelAndView add() {
    	ModelAndView mv = new ModelAndView();
    	
    	mv.setViewName("department_add");
    	
        return mv;
    }
    
	@RequestMapping(value = "/save")
    public String save(HttpServletRequest request) {
    	String department_id = request.getParameter("department_id").trim();
    	String departmentname = request.getParameter("departmentname").trim();
    	
    	Zzdepartment sql_department = new Zzdepartment();
    	
    	sql_department.setDepartment_id(department_id);
    	sql_department.setDepartmentname(departmentname);
    	
    	zzdepartmentService.insert(sql_department);
    	
    	return "redirect:/department/list";
    }
	
    @RequestMapping(value = "/list")
    public String list(Model model) {
    	List<Zzdepartment> datas=zzdepartmentService.listAll(null);   	
    	model.addAttribute("groups", datas);    	
    	return "department_list";
    }
    
    
    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable String id){
    	ModelAndView mv = new ModelAndView();
    	
    	Zzdepartment data = zzdepartmentService.selectByPrimaryKey(id);
    	
        mv.setViewName("department_view");
    	mv.addObject("department", data);
    	return mv;
    }  
    
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id){
    	ModelAndView mv = new ModelAndView();
    	
    	Zzdepartment data = zzdepartmentService.selectByPrimaryKey(id);
    	
    	mv.addObject("department", data);
    	mv.setViewName("department_edit");
    	return mv;
    }  
    
    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request) {
    	String department_id = request.getParameter("department_id").trim();
    	String departmentname = request.getParameter("departmentname").trim();
    	
    	Zzdepartment sql_department = new Zzdepartment();
    	
    	sql_department.setDepartment_id(department_id);
    	sql_department.setDepartmentname(departmentname);
    	
    	zzdepartmentService.update(sql_department);
    	
    	return "redirect:/department/list";
    }
    
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable String id){
    	zzdepartmentService.deleteByPrimaryKey(id);
    	return "redirect:/department/list";
    }  
    
}
