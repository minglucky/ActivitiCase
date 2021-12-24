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

import com.zioer.model.Zzrole;
import com.zioer.service.ZzroleService;

@Controller
@RequestMapping(value = "/role")
public class ZzroleController {
    
	@Autowired
	protected IdentityService identityService;
	@Autowired
	protected ZzroleService zzroleService;
    
	@RequestMapping(value = "/add")
    public ModelAndView add() {
    	ModelAndView mv = new ModelAndView();
    	
    	mv.setViewName("role_add");
    	
        return mv;
    }
    
	@RequestMapping(value = "/save")
    public String save(HttpServletRequest request) {
    	String role_id = request.getParameter("role_id").trim();
    	String rolename = request.getParameter("rolename").trim();
    	
    	Zzrole sql_role = new Zzrole();
    	
    	sql_role.setRole_id(role_id);
    	sql_role.setRolename(rolename);
    	
    	zzroleService.insert(sql_role);
    	
    	return "redirect:/role/list";
    }
	
    @RequestMapping(value = "/list")
    public String list(Model model) {
    	List<Zzrole> datas=zzroleService.listAll(null);   	
    	model.addAttribute("groups", datas);    	
    	return "role_list";
    }
    
    
    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable String id){
    	ModelAndView mv = new ModelAndView();
    	
    	Zzrole data = zzroleService.selectByPrimaryKey(id);
    	
        mv.setViewName("role_view");
    	mv.addObject("role", data);
    	return mv;
    }  
    
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id){
    	ModelAndView mv = new ModelAndView();
    	
    	Zzrole data = zzroleService.selectByPrimaryKey(id);
    	
    	mv.addObject("role", data);
    	mv.setViewName("role_edit");
    	return mv;
    }  
    
    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request) {
    	String role_id = request.getParameter("role_id").trim();
    	String rolename = request.getParameter("rolename").trim();
    	
    	Zzrole sql_role = new Zzrole();
    	
    	sql_role.setRole_id(role_id);
    	sql_role.setRolename(rolename);
    	
    	zzroleService.update(sql_role);
    	
    	return "redirect:/role/list";
    }
    
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable String id){
    	zzroleService.deleteByPrimaryKey(id);
    	return "redirect:/role/list";
    }  
    
}
