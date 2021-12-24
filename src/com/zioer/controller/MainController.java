package com.zioer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zioer.model.Zzuser;
import com.zioer.service.ZzuserService;

@Controller
@RequestMapping(value = "/main")
public class MainController {
	
	@Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ZzuserService zzuserService;
    
    @RequestMapping(value = "/")
    public String index() {
    	return "main";
    }

    @RequestMapping(value = "/left")
    public String left(Model model,HttpSession session) {
    	if (session.getAttribute("userId") == null){
    		return "redirect:/login/";
    	}
    	model.addAttribute("userId", session.getAttribute("userId").toString());
    	return "left";
    }

    @RequestMapping(value = "/top")
    public String top() {
    	return "top";
    }

    @RequestMapping(value = "/main")
    public String main(Model model,HttpSession session) {
    	//进入系统门户页面
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}
    	List<Task> tasks = new ArrayList<Task>();
    	List<Map> tasklist = new ArrayList<Map>();
    	
    	Zzuser currentUser = zzuserService.selectByPrimaryKey(userId);
    	String group = currentUser.getDepartment_id()+ ":"+ currentUser.getRole_id();
    	
    	//获得当前用户的待处理和待接收的任务
    	tasks = taskService.createTaskQuery()
    			.taskCandidateOrAssigned(userId) //直接分配给用户的方式
    			.taskCandidateGroup(group)	//任务是否分配给组
    			.active()
    			.orderByTaskId().desc().list();

    	for(int i = 0;i<tasks.size();i++){
    		Map<String, Object> map = new HashMap<String, Object>();
    		Task task = tasks.get(i);
    		String processDefinitionId = task.getProcessDefinitionId();
    		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
    				.processDefinitionId(processDefinitionId)
    				.singleResult();
    		
    		map.put("processDefinitionName", processDefinition.getName());
    		map.put("processDefinitionKey", processDefinition.getKey().toLowerCase());
    		map.put("taskName", task.getName());
    		map.put("taskId", task.getId());
    		map.put("assignee", task.getAssignee());
    		map.put("createTime", task.getCreateTime());
    		
    		tasklist.add(map);
    		
    	}
    	
    	//获得当前用户的历史
    	List<Map> hlist = new ArrayList<Map>();
    	List historylist = historyService.createHistoricProcessInstanceQuery()
                .startedBy(userId)
                .orderByProcessInstanceEndTime()
                .asc()
                .list();
    	
    	for (int i=0;i<historylist.size();i++){
    		Map<String, Object> map = new HashMap<String, Object>();
    		HistoricProcessInstanceEntity hpe = (HistoricProcessInstanceEntity) historylist.get(i); 
    		
    		map.put("id", hpe.getId());
    		map.put("startUserId", hpe.getStartUserId());
    		map.put("processInstanceId", hpe.getProcessInstanceId());
    		map.put("processDefinitionKey", hpe.getProcessDefinitionKey().toLowerCase());
    		map.put("ProcessDefinitionName", hpe.getProcessDefinitionName());
    		map.put("superProcessInstanceId", hpe.getSuperProcessInstanceId());
    		
    		if (hpe.getSuperProcessInstanceId()!="" && hpe.getSuperProcessInstanceId() != null){
    			HistoricProcessInstance superHistoricProcessInstance = historyService.createHistoricProcessInstanceQuery()
    	    	    .processInstanceId(hpe.getSuperProcessInstanceId()).singleResult();
    			String superProcessDefinitionName = superHistoricProcessInstance.getProcessDefinitionName();
    			map.put("superProcessDefinitionName", superProcessDefinitionName);
    		}
    		
    		map.put("endTime", hpe.getEndTime());
    		map.put("startTime", hpe.getStartTime());
    		if (hpe.getEndTime() == null){
    			List<Task> taskList =  taskService.createTaskQuery().processInstanceId(hpe.getProcessInstanceId()).active().list();
    			String taskName = "";
    			String taskId = "";
    			for (int j=0;j<taskList.size();j++){
    				if (taskList.get(j) != null){
    					taskName = taskName == "" ? taskList.get(j).getName() : taskName + "," + taskList.get(j).getName(); 
    					taskId = taskId == "" ? taskList.get(j).getId() : taskId + "," + taskList.get(j).getId(); 
    				}
    			}
    			if (taskName != ""){
    				map.put("name", taskName);
    			}
    			if (taskId != ""){
    				map.put("taskId", taskId);
    			}
    			
    		}else{
    			map.put("name", "已完成");
    		}
    		hlist.add(map);
    	}
    	
    	model.addAttribute("tasklist", tasklist);
    	model.addAttribute("hlist", hlist);
    	
    	return "mainfra";
    }

    
}
