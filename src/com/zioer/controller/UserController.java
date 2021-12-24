package com.zioer.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zioer.model.Zzdepartment;
import com.zioer.model.Zzrole;
import com.zioer.model.Zzuser;
import com.zioer.service.ZzdepartmentService;
import com.zioer.service.ZzroleService;
import com.zioer.service.ZzuserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private FormService formService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ZzuserService zzuserService;
    @Autowired
    private ZzroleService zzroleService;
    @Autowired
    private ZzdepartmentService zzdepartmentService;
    
    @RequestMapping(value = "/add")
    public ModelAndView add() {
    	ModelAndView mv = new ModelAndView();
    	
    	List<Zzrole> roleList =  zzroleService.listAll(null);
    	List<Zzdepartment> departmentList =  zzdepartmentService.listAll(null);
    	
    	mv.setViewName("user_add");
    	mv.addObject("roleList", roleList);
    	mv.addObject("departmentList", departmentList);
    	
        return mv;
    }
    
	
    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request) {
    	String user_id = request.getParameter("userid").trim();
    	String username = request.getParameter("username").trim();
    	String department_id = request.getParameter("department_id").trim();
    	String role_id = request.getParameter("role_id").trim();
    	String pwd = request.getParameter("pwd").trim();
    	
    	Zzuser sql_user = new Zzuser();
    	
    	sql_user.setUser_id(user_id);
    	sql_user.setUsername(username);
    	sql_user.setDepartment_id(department_id);
    	sql_user.setRole_id(role_id);
    	sql_user.setPsd(pwd);
    	
    	zzuserService.insert(sql_user);
    	
    	return "redirect:/user/list";
    }
    
    @RequestMapping(value = "/list")
    public String list(Model model) {
    	List<Zzuser> datas = zzuserService.listAll(null);    	   	
    	model.addAttribute("users", datas);    	
    	return "user_list";
    }
    
    @RequestMapping(value = "/pagelist")
    public String pagelist(Model model,HttpServletRequest request) {
    	int pageSize = 10;
    	int page;
    	long totalPage = 0;
    	long totalRows = 0;
    	
    	int firstResult;	//查询的起始记录数
    	int maxResults = pageSize; //查询的每页显示记录数
    	Zzuser sql_user = new Zzuser();
    	
    	totalRows = zzuserService.listAllCount(sql_user);
    	if ( totalRows % pageSize == 0){
    		totalPage = totalRows / pageSize;
    	}else{
    		totalPage = totalRows / pageSize + 1;
    	}
    	
    	if (request.getParameter("page") == null){
    		page = 1;
    	}else{
    		page = Integer.parseInt(request.getParameter("page")) ;
    		if (page<1) {
    			page = 1;
    		}
    		else if (page > totalPage) {
    			page = (int)totalPage;
    		}
    	}
    	
    	firstResult = pageSize * (page - 1);
    	
    	sql_user.setFirstResult(firstResult);
    	sql_user.setMaxResults(maxResults);
    	
    	List<Zzuser> datas = zzuserService.listAll(sql_user);

    	model.addAttribute("users", datas);
    	model.addAttribute("currentPage", page);
    	model.addAttribute("totalPage", totalPage);
    	
    	return "user_list2";
    }
    
    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable String id){
    	ModelAndView mv = new ModelAndView();
    	Zzuser current_user = zzuserService.selectByPrimaryKey(id);
    	
    	mv.setViewName("user_view");
    	mv.addObject("user", current_user);
    	
    	return mv;
    }  
    
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id){
    	ModelAndView mv = new ModelAndView();
    	
    	List<Zzrole> roleList =  zzroleService.listAll(null);
    	List<Zzdepartment> departmentList =  zzdepartmentService.listAll(null);
    	
    	mv.addObject("roleList", roleList);
    	mv.addObject("departmentList", departmentList);
    	
    	Zzuser current_user = zzuserService.selectByPrimaryKey(id);
    	
    	mv.setViewName("user_edit");
    	mv.addObject("roleList", roleList);
    	mv.addObject("departmentList", departmentList);    	
    	mv.addObject("user", current_user);
    	return mv;
    }  
    
    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request) {
    	String user_id = request.getParameter("userid").trim();
    	String username = request.getParameter("username").trim();
    	String department_id = request.getParameter("department_id").trim();
    	String role_id = request.getParameter("role_id").trim();
    	String pwd = request.getParameter("pwd").trim();
    	
    	Zzuser sql_user = new Zzuser();
    	
    	sql_user.setUser_id(user_id);
    	sql_user.setUsername(username);
    	sql_user.setDepartment_id(department_id);
    	sql_user.setRole_id(role_id);
    	sql_user.setPsd(pwd);
    	
    	zzuserService.update(sql_user);
    	
        return "redirect:/user/list";
    }
    
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable String id){
    	zzuserService.deleteByPrimaryKey(id);
    	return "redirect:/user/list";
    }  
   
    /**
     * Form初始化启动流程，读取启动流程的表单字段来渲染start form
     */
    @RequestMapping(value = "/start")
    public ModelAndView StartFormProcess() throws Exception {
    	ModelAndView mv = new ModelAndView();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("reimbursement").latestVersion().singleResult();

        StartFormData startFormData = formService.getStartFormData(processDefinition.getId());
        
        List<FormProperty> formProperties = startFormData.getFormProperties();
        
        mv.setViewName("reimbursement_start");
    	mv.addObject("list", formProperties);
    	mv.addObject("formData", startFormData);
    	
    	return mv;
    }
    
    /**
     * 提交启动流程
     */
    @RequestMapping(value = "/start/save")
    public ModelAndView saveStartForm(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView();
    	Map<String, String> formProperties = PageData(request);
        ProcessInstance processInstance = null;
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("reimbursement").latestVersion().singleResult();
        String processDefinitionId = processDefinition.getId();
        try {
            identityService.setAuthenticatedUserId("zioer");
            processInstance = formService.submitStartFormData(processDefinitionId, formProperties);
        } finally {
            identityService.setAuthenticatedUserId(null);
        }

        mv.setViewName("group_list");

    	return mv;
    }

    /**
     * 初始化启动流程，读取启动流程的表单字段来渲染start form
     */
    @RequestMapping(value = "/startform")
    public ModelAndView StartTaskForm() throws Exception {
    	List<Task> tasks = new ArrayList<Task>();
    	
    	//获得用户lee的任务
    	tasks = taskService.createTaskQuery().taskCandidateOrAssigned("lobby").active().orderByTaskId().desc().list();
    	String taskId =  null;
    	for(int i= 0;i<tasks.size();i++){
    		taskId = tasks.get(i).getId();
    		break;
        }
        
    	ModelAndView mv = new ModelAndView();
//
//    	Map<String, Object> result = new HashMap<String, Object>();
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
//        // 设置task为null，否则输出json的时候会报错
        
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        
        mv.addObject("formData", taskFormData);
        mv.addObject("list", formProperties);
        mv.setViewName("reimbursement_edit");
        return mv;
    }
    /**
     * 提交启动流程
     */
    @RequestMapping(value = "/startform/save/{taskId}")
    public ModelAndView saveTaskForm(@PathVariable("taskId") String taskId,HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView();
    	Map formProperties = PageData(request);
        
        try {
            identityService.setAuthenticatedUserId("lobby");
            formService.submitTaskFormData(taskId, formProperties);
        } finally {
            identityService.setAuthenticatedUserId(null);
        }

        mv.setViewName("group_list");

    	return mv;
    }
    
    public Map PageData(HttpServletRequest request){
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		return returnMap;
	}
}
