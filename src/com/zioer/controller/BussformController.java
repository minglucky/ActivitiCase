package com.zioer.controller;

import com.zioer.model.*;
import com.zioer.service.ReimbursementService;
import com.zioer.service.ZlogService;
import com.zioer.service.ZzuserService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping(value = "/reimbursement")
public class BussformController {

	private static String processDefinitionKey = "reimbursement";
	//private static String processDefinitionKey = "bussform";
    
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
    private ReimbursementService reimbursementService;
	@Autowired
	private ZzuserService zzuserService;
	@Autowired
	private ZlogService zlogService;
    
    @RequestMapping(value = "/add")
    public String add(Model model, HttpSession session) {
    	if (session.getAttribute("userId") == null){
    		return "redirect:/login/";
    	}

    	return "reimbursement_start";
    }
    
    /**
     * 提交启动流程
     */
    @RequestMapping(value = "/start/save")
    public String saveStartForm(Model model, Reimbursement reimbursement, HttpServletRequest request, HttpSession session) throws ParseException {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Map<String, Object> map = new HashMap<String, Object>();
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replace("-", "");

		String fee = request.getParameter("fee") == null ? null : request.getParameter("fee").toString().trim();
		String type = request.getParameter("type") == null ? null : request.getParameter("type").toString().trim();
		String feedate = request.getParameter("feedate") == null ? null : request.getParameter("feedate").toString().trim();
		String note = request.getParameter("note") == null ? null : request.getParameter("note").toString().trim();

		Zzuser currentUser = zzuserService.selectByPrimaryKey(userId);

		reimbursement.setFee(Double.parseDouble(fee));
		reimbursement.setType(type);
		reimbursement.setFeedate(sdf.parse(feedate));
		reimbursement.setNote(note);
    	reimbursement.setUserId(userId);
    	reimbursement.setCreatedatetime(new Date());
		reimbursement.setId(uuidStr);
		reimbursementService.insert(reimbursement);
    	String businessKey = reimbursement.getId();
    	
    	try{
			map.put("role", currentUser.getRole_id());	//流程变量：职务，分支使用

			if (currentUser.getRole_id().equals("002")){
				map.put("leader_department", currentUser.getDepartment_id()+":001");	//流程变量：如果是职员，设置当前部门领导
			}

			ProcessDefinition processDefinition = repositoryService
    				.createProcessDefinitionQuery()
    				.processDefinitionKey(processDefinitionKey)
    				.latestVersion().singleResult();
            String processDefinitionId = processDefinition.getId();

			System.out.println("processDefinitionId" + processDefinitionId);
    		identityService.setAuthenticatedUserId(userId);
			//以businessKey方式：启动流程
			ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey, map);

			reimbursement.setPid(processInstance.getId()); //获取和设置Pid
            reimbursementService.update(reimbursement);
         } finally {
             identityService.setAuthenticatedUserId(null);
         }

        return "redirect:../list";
    }

    @RequestMapping(value = "/list")
    public String list(Model model, HttpSession session) {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}

		Zzuser currentUser = zzuserService.selectByPrimaryKey(userId);
		String group = currentUser.getDepartment_id()+ ":"+ currentUser.getRole_id();
    	List<Task> tasks = new ArrayList<Task>();
    	
    	//获得当前用户的任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(processDefinitionKey)
				.taskCandidateOrAssigned(userId) //直接分配给用户的方式
				.taskCandidateGroup(group)	//任务是否分配给组
				.active()
				.orderByTaskId().desc().list();
    	
    	model.addAttribute("list", tasks);
    	
    	return "reimbursement_list";
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
	public String StartTaskForm(@PathVariable("taskId") String taskId,Model model, HttpSession session) throws Exception {
		String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
		if (userId == null){
			return "redirect:/login/";
		}

		String actId = null;
		String bId = null;
		String username = null;
		String retView = null;
		String taskname = null;
		Reimbursement record = null;
		List<Zlog> logList = null;

		try{

			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			String pId = task.getProcessInstanceId();
			ProcessInstance pins = runtimeService.createProcessInstanceQuery()
					.processInstanceId(pId)
					.active().singleResult();

			bId = pins.getBusinessKey();
			actId = pins.getActivityId();
			record = reimbursementService.selectByPrimaryKey(bId);
			taskname = task.getName();

			switch(task.getTaskDefinitionKey()) {
				case "usertask1":
					retView="reimbursement_edit";	//部门领导确认
					break;
				case "usertask2":
					retView="reimbursement_edit";	//财务部门确认
					break;
				case "usertask3":
					retView="reimbursement_confirm";	//申请人确认
					break;
				default:
			}

			username = zzuserService.selectByPrimaryKey(record.getUserId()).getUsername();

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
     */
    @RequestMapping(value = "/startform/save/{taskId}")
    public String saveTaskForm(@PathVariable("taskId") String taskId, HttpSession session, Reimbursement reimbursement) {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}

		Map<String, Object> map = new HashMap<String, Object>();
		String retView = "redirect:../../list";
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replace("-", "");

        try {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

			String pId = task.getProcessInstanceId();
			ProcessInstance pins = runtimeService.createProcessInstanceQuery()
					.processInstanceId(pId)
					.active().singleResult();

			String bId = pins.getBusinessKey();
			task = taskService.createTaskQuery().taskId(taskId).singleResult();
        	switch (task.getTaskDefinitionKey()) {
				case "usertask1":
					map.put("leader_department", "002:002");
					break;
				case "usertask2":
					map.put("processInstanceId", pId);
					System.out.println("*****************************" + map);
				break;
				default:

			}

			Zlog data = new Zlog();

			data.setId(uuidStr);
			data.setTask(processDefinitionKey);
			data.setTask_id(bId);
			data.setUser_id(userId);
			data.setCreatedatetime(new Date());
            identityService.setAuthenticatedUserId(userId);
            taskService.complete(taskId, map);
            reimbursementService.update(reimbursement);
        } finally {
            identityService.setAuthenticatedUserId(null);
        }

        return retView;
    }

    @RequestMapping(value = "/hlist")
    public String historylist(Model model, HttpSession session) {
    	String userId = session.getAttribute("userId") == null ? null : session.getAttribute("userId").toString();
    	if (userId == null){
    		return "redirect:/login/";
    	}
    	    	
    	List<Map> hlist = new ArrayList<Map>();
    	List historylist = historyService.createHistoricProcessInstanceQuery()
    			.processDefinitionKey("reimbursement-2")
                .startedBy(userId).list();

    	for (int i=0;i<historylist.size();i++){
    		Map<String, Object> map = new HashMap<String, Object>();
    		HistoricProcessInstanceEntity hpe = (HistoricProcessInstanceEntity) historylist.get(i);
    		
    		map.put("id", hpe.getId());
    		map.put("startUserId", hpe.getStartUserId());
    		map.put("processInstanceId", hpe.getProcessInstanceId());
    		map.put("endTime", hpe.getEndTime());
    		map.put("startTime", hpe.getStartTime());
    		if (hpe.getEndTime() == null){
    			Task task =  taskService.createTaskQuery().processInstanceId(hpe.getProcessInstanceId()).active().singleResult();
    			if (task != null){
    				map.put("name", task.getName());
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

		Reimbursement record = null;
		List<Zlog> logList = null;
		Zlog sql_data = new Zlog();

		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processDefinitionKey).processInstanceId(pId).singleResult();

		//获取业务表单数据
		String bId = hpi.getBusinessKey();
		record = reimbursementService.selectByPrimaryKey(bId);


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
				Reimbursement reimbursement = reimbursementService.selectByPrimaryKey(pid);
				model.addAttribute("reimbursement", reimbursement);
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
					Reimbursement reimbursement = reimbursementService.selectByPrimaryKey(pid);
					model.addAttribute("reimbursement", reimbursement);
				}
			}


		}

		sql_data.setTask(processDefinitionKey);
		sql_data.setTask_id(bId);

		logList = zlogService.listAll(sql_data);

		model.addAttribute("data", record);
		model.addAttribute("logList", logList);
		model.addAttribute("taskName", taskName);

		return "reimbursement_hview";
	}
    
    public Map PageData(HttpServletRequest request){
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
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
