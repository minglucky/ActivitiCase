package com.zioer.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zioer.model.Paiche;
import com.zioer.model.ZBeaway;
import com.zioer.model.Zlog;
import com.zioer.model.Zzuser;
import com.zioer.service.PaicheService;
import com.zioer.service.ZBeawayService;
import com.zioer.service.ZlogService;
import com.zioer.service.ZzuserService;
import com.zioer.service.Imp.ServiceTaskDemo3;
import com.zioer.util.PaicheManage;

@Controller
@RequestMapping(value = "/beaway")
public class BeawayController {
	
	private static String processDefinitionKey = "beAway";
	
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
    private ZBeawayService zBeawayService;
    
    @Autowired
    private ZzuserService zzuserService;
    @Autowired
    private ZlogService zlogService;
    @Autowired
	private PaicheManage paicheManage;
    
    @RequestMapping(value = "/add")
    public String add(Model model,HttpSession session) {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}
    	        
    	return "beaway_start";
    }

    /**
     * 提交启动流程
     */
    @RequestMapping(value = "/start/save")
    public String saveStartForm(Model model,HttpServletRequest request,HttpSession session) {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	UUID uuid = UUID.randomUUID();
    	String uuidStr = uuid.toString().replace("-", "");
    	String businessKey = uuidStr;
    	
    	ZBeaway record = new ZBeaway();
    	String sort = request.getParameter("sort") == null ? null : request.getParameter("sort").toString().trim();
    	String onposition = request.getParameter("onposition") == null ? null : request.getParameter("onposition").toString().trim();
    	String startdatetime = request.getParameter("startdatetime") == null ? null : request.getParameter("startdatetime").toString().trim();
    	String enddatetime = request.getParameter("enddatetime") == null ? null : request.getParameter("enddatetime").toString().trim();
    	String phone = request.getParameter("phone") == null ? null : request.getParameter("phone").toString().trim();
    	String bzhu = request.getParameter("bzhu") == null ? null : request.getParameter("bzhu").toString().trim();
    	
    	Zzuser currentUser = zzuserService.selectByPrimaryKey(userId);
    	
    	try{
    		record.setSort(sort);
    		record.setOnposition(onposition);
    		record.setStartdatetime(sdf.parse(startdatetime));
    		record.setEnddatetime(sdf.parse(enddatetime));
        	record.setPhone(phone);
        	record.setBzhu(bzhu);
        	record.setId(uuidStr);
        	record.setUser_id(userId);
        	record.setCreatedatetime(new Date());
        	
        	map.put("role", currentUser.getRole_id());	//流程变量：职务，分支使用
        	
    		if (currentUser.getRole_id().equals("002")){
    			map.put("leader_department", currentUser.getDepartment_id()+":001");	//流程变量：如果是职员，设置当前部门领导
    		}else{
    			map.put("leader_department", "001:001");	//流程变量：如果是部门领导，直接设置经理部门领导
    		}
    		
    		ProcessDefinition processDefinition = repositoryService
    				.createProcessDefinitionQuery()
    				.processDefinitionKey(processDefinitionKey)
    				.latestVersion().singleResult();
    		
    		String processDefinitionId = processDefinition.getId();	
    		
    		identityService.setAuthenticatedUserId(userId);
    		
    		//以businessKey方式：启动流程
    		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey, map);
    		
    		record.setPid(processInstance.getId()); //获取和设置Pid
    		zBeawayService.insert(record);	//业务数据保存
    	}catch(Exception e){
    		System.out.println("error : " + e.toString());
    	}finally {
    		 identityService.setAuthenticatedUserId(null);
    	}

        return "redirect:../list";
    }
    
    @RequestMapping(value = "/list")
    public String list(Model model,HttpSession session) {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}

    	Zzuser currentUser = zzuserService.selectByPrimaryKey(userId);
    	String group = currentUser.getDepartment_id()+ ":"+ currentUser.getRole_id();
    	List<Task> tasks = new ArrayList<Task>();
    	
    	//获得当前用户的待处理和待接收的任务
    	tasks = taskService.createTaskQuery()
    			.processDefinitionKey(processDefinitionKey)
    			.taskCandidateOrAssigned(userId) //直接分配给用户的方式
    			.taskCandidateGroup(group)	//任务是否分配给组
    			.active()
    			.orderByTaskId().desc().list();
    	
    	model.addAttribute("list", tasks);
    	
    	return "beaway_list";
    }
    
    /**
     * 任务签收
     */
    @RequestMapping(value = "/claim/{taskId}")
    public String claim(@PathVariable("taskId") String taskId, HttpSession session) {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}
    	
        taskService.claim(taskId, userId);
        return "redirect:../list";
    }
    
    /**
     * 开始中间任务节点流程
     */
    @RequestMapping(value = "/startform/{taskId}")
    public String StartTaskForm(@PathVariable("taskId") String taskId,Model model,HttpSession session) throws Exception {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}
    	
    	String actId = null;
    	String bId = null;
    	String username = null;
    	String retView = null;
    	String taskname = null;
    	ZBeaway record = null;
    	List<Zlog> logList = null;
    	
    	try{
    		
    		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    		String pId = task.getProcessInstanceId();
    		ProcessInstance pins = runtimeService.createProcessInstanceQuery()
    				.processInstanceId(pId)
    				.active().singleResult();
    		
    		bId = pins.getBusinessKey();
    		actId = pins.getActivityId();
    		record = zBeawayService.selectByPrimaryKey(bId);
    		taskname = task.getName();
    		
    		switch(task.getTaskDefinitionKey()) {
	            case "usertask1":
	            	
	            case "usertask2":
	            	retView="beaway_bumen_leader_approval";	//部门领导确认
	                break;
	            case "usertask3":
	            	retView="beaway_money";	//填写借钱单据
	                break;
	            case "usertask4":
	            	retView="beaway_money_approval";	//财务职员办理
	                break;
	            case "usertask5":
	            	retView="beaway_car";	//填写派车单
	                break;
	            case "usertask6":
	            	retView="beaway_view";	//申请人确认
	                break;
	            default:
	            	retView="paiche_edit";
	         }
    		
    		username = zzuserService.selectByPrimaryKey(record.getUser_id()).getUsername();
        	
    		Zlog sql_data = new Zlog();
    		sql_data.setTask(processDefinitionKey);
    		sql_data.setTask_id(bId);
    		
    		logList = zlogService.listAll(sql_data);
    		
    	}catch(Exception e){
    		System.out.println(e.toString());
    	}
    	
    	model.addAttribute("data", record);
    	model.addAttribute("taskId", taskId);
    	model.addAttribute("actId", actId);
    	model.addAttribute("taskname", taskname);
    	model.addAttribute("startUsername", username);
    	model.addAttribute("logList", logList);
        
        return retView;
    }
    /**
     * 提交和保存中间任务节点
     * @throws ParseException 
     */
    @RequestMapping(value = "/startform/save/{taskId}")
    public String saveTaskForm(@PathVariable("taskId") String taskId,HttpSession session,HttpServletRequest request) throws ParseException {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}

    	Map<String, Object> map = new HashMap<String, Object>();
    	String retView = "redirect:../../list";
    	
    	UUID uuid = UUID.randomUUID();
    	String uuidStr = uuid.toString().replace("-", "");
    	
    	try {
    		String approval_2 = request.getParameter("approval_2") == null ? null : request.getParameter("approval_2").toString().trim();
    		String log = request.getParameter("log") == null ? null : request.getParameter("log").toString().trim();
        	
    		identityService.setAuthenticatedUserId(userId);
    		
    		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    		
    		String pId = task.getProcessInstanceId();
    		ProcessInstance pins = runtimeService.createProcessInstanceQuery()
    				.processInstanceId(pId)
    				.active().singleResult();
    		
    		String bId = pins.getBusinessKey();
    		
    		map.put("sort", zBeawayService.selectByPrimaryKey(bId).getSort());
    		//对各个任务节点需要分别进行处理
    		switch(task.getTaskDefinitionKey()) {
	            case "usertask1":

	            	break;
	            case "usertask2":
	                break;
	            case "usertask3":
	            	String borrowmoney = request.getParameter("borrowmoney") == null ? null : request.getParameter("borrowmoney").toString().trim();
	            	
	            	ZBeaway record = new ZBeaway();
	            	record.setId(bId);
	            	record.setBorrowmoney(Double.parseDouble(borrowmoney));

	            	zBeawayService.update(record);
	            	
	            	map.put("leader_department", "002:002");	//财务人员办理
	                break;
	            case "usertask4": //
	                break;
	            case "usertask5": //车辆申请	            	
	            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	
	            	String pstartdatetime = request.getParameter("pstartdatetime") == null ? null : request.getParameter("pstartdatetime").toString().trim();
	            	String startposition = request.getParameter("startposition") == null ? null : request.getParameter("startposition").toString().trim();
	            	String endposition = request.getParameter("endposition") == null ? null : request.getParameter("endposition").toString().trim();
	            	String persons = request.getParameter("persons") == null ? null : request.getParameter("persons").toString().trim();
	            	String phone = request.getParameter("phone") == null ? null : request.getParameter("phone").toString().trim();
	            	
	            	Zzuser currentUser = zzuserService.selectByPrimaryKey(userId);
	            	
	            	map = new HashMap<String, Object>();
	            	
	            	map.put("role", currentUser.getRole_id());	//流程变量：职务，分支使用
	            	if (currentUser.getRole_id().equals("002")){
	        			map.put("leader_department", currentUser.getDepartment_id()+":001");	//流程变量：如果是职员，设置当前部门领导
	        		}else{
	        			map.put("leader_department", "003:001");	//流程变量：如果是部门领导，直接设置车管部门领导
	        		}
	        		
	            	Paiche paicheModel = new Paiche();
	            							
					paicheModel.setStartdatetime(sdf.parse(pstartdatetime));						
	            	paicheModel.setStartposition(startposition);
	            	paicheModel.setEndposition(endposition);
	            	paicheModel.setPersons(Integer.parseInt(persons));
	            	paicheModel.setPhone(phone);
	            	paicheModel.setBzhu("出差");
	            	paicheModel.setUser_id(userId);
	            	paicheModel.setCreatedatetime(new Date());
	            	
	            	String bid = paicheManage.save(paicheModel);	//业务数据保存:派车Id	
	            	
	            	map.put("processBusinessKey", bid);
	            	
	                break;
	            default:
	            	//;
	         }
    		
    		taskService.complete(taskId, map);
    		
    		//Record Log
    		Zlog data = new Zlog();
        	
        	data.setId(uuidStr);
        	data.setTask(processDefinitionKey);
        	data.setTask_id(bId);
        	data.setUser_id(userId);
        	data.setIsagreed(approval_2);
        	data.setLog(log);
        	data.setCreatedatetime(new Date());
        	
        	zlogService.insert(data);
    	} finally {
    		identityService.setAuthenticatedUserId(null);
    	}

        return retView;
    }

    @RequestMapping(value = "/hlist")
    public String historylist(Model model,HttpSession session) {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}
    	    	
    	List<Map> hlist = new ArrayList<Map>();
    	List historylist = historyService.createHistoricProcessInstanceQuery()
    			.processDefinitionKey(processDefinitionKey)
                .startedBy(userId).list();

    	for (int i=0;i<historylist.size();i++){
    		Map<String, Object> map = new HashMap<String, Object>();
    		HistoricProcessInstanceEntity hpe = (HistoricProcessInstanceEntity) historylist.get(i); 
    		
    		map.put("id", hpe.getId());
    		map.put("startUserId", hpe.getStartUserId());
    		map.put("processInstanceId", hpe.getProcessInstanceId());
    		map.put("processDefinitionKey", hpe.getProcessDefinitionKey());
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
    			for (int j=0;j<taskList.size();j++){
    				if (taskList.get(j) != null){
    					taskName = taskName == "" ? taskList.get(j).getName() : taskName + "," + taskList.get(j).getName(); 
    				}
    				
    			}
    			if (taskName != ""){
    				map.put("name", taskName);
    			}
    		}else{
    			map.put("name", "已完成");
    		}
    		hlist.add(map);
    	}
    	
    	//获得当前用户的任务
    	model.addAttribute("list", hlist);
    	
    	return "reimbursement_hlist";
    }
    
    @RequestMapping(value = "/hview/{pId}")
    public String historyView(@PathVariable("pId") String pId,Model model,HttpSession session) {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}
    	
    	ZBeaway record = null;
    	List<Zlog> logList = null;
    	Zlog sql_data = new Zlog();
    	
    	HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processDefinitionKey).processInstanceId(pId).singleResult();
    	
    	//获取业务表单数据
    	String bId = hpi.getBusinessKey();
		record = zBeawayService.selectByPrimaryKey(bId);
		
		
		String taskName = "";
		String pid = "";
		if (hpi.getEndTime() == null){
			//表示还没有结束流程
			HistoricProcessInstanceEntity hpe = (HistoricProcessInstanceEntity) hpi;
			
			pid = (String)runtimeService.getVariable(hpe.getProcessInstanceId(), "processBusinessKey");
			
			List<Task> taskList2 =  taskService.createTaskQuery().processInstanceId( hpe.getProcessInstanceId() ).active().list();
			List<Task> taskList = taskService.createTaskQuery()
					.processVariableValueEquals("processBusinessKey", pid )
					.active()
					.list();
			
			taskList.addAll(taskList2);
			List<String> taskNameList_Dup = new ArrayList<>();
			for (int j=0;j<taskList.size();j++){
				if (taskList.get(j) != null){
					taskNameList_Dup.add(taskList.get(j).getName());
				}
			}
			List<String> taskNameList = new ArrayList<>(new HashSet<>(taskNameList_Dup)); //去重
			taskName = listToString(taskNameList);
			
			List<Task> tasks = taskService.createTaskQuery()
					.processVariableValueEquals("processBusinessKey", pid ).list();

			for (int j=0;j<tasks.size();j++){
				if (tasks.get(j) != null){
					System.out.println(tasks.get(j).getName());
				}
			}
			
			if (pid != null){
				Paiche paiche = paicheManage.getOne(pid);
				model.addAttribute("paiche", paiche);
			}
		}else{
			HistoricVariableInstance historicVariableInstance = historyService
	      		.createHistoricVariableInstanceQuery()
	      		.processInstanceId(hpi.getId())
	      		.variableName("processBusinessKey")
	      		.singleResult();
	        
			if (historicVariableInstance != null){
				pid = (String)historicVariableInstance.getValue();
		        if (pid != null){
					Paiche paiche = paicheManage.getOne(pid);
					model.addAttribute("paiche", paiche);
				}
			}
	        

		}
		
		sql_data.setTask(processDefinitionKey);
		sql_data.setTask_id(bId);
		
		logList = zlogService.listAll(sql_data);
    	
		model.addAttribute("data", record);
		model.addAttribute("logList", logList);
		model.addAttribute("taskName", taskName);
        
    	return "beaway_hview";
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
	
    public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }

}
