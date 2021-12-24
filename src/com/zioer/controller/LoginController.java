package com.zioer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    
    @Autowired
    private IdentityService identityService;
    
    @RequestMapping(value = "/")
    public String add() {
        return "login";
    }
    
    @RequestMapping(value = "/out")
    public String out(HttpSession session) {
    	session.removeAttribute("userId");
    	return "login";
    }
    
    
    
    @RequestMapping(value = "/save",method=RequestMethod.POST)
    public String save(@RequestParam(value="username") String username, @RequestParam(value="password") String password, HttpSession session,HttpServletRequest request) {
    	session.removeAttribute("userId");
    	
    	boolean canPass = identityService.checkPassword(username, password);
    	
    	if (canPass){
    		session.setAttribute("userId", username);
    	}else{
    		return "redirect:/login/";
    	}
    	
    	return "redirect:/main/";
    }
    
    
}
