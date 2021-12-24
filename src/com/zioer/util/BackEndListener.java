package com.zioer.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.zioer.model.Paiche;
import com.zioer.service.PaicheService;

//@Service
public class BackEndListener implements  JavaDelegate {
//休假出差流程子流程返回用
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("end");
		System.out.println(execution.getCurrentActivityId());
		System.out.println(execution.getCurrentActivityName());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
//		execution
//		UUID uuid = UUID.randomUUID();
//    	String uuidStr = uuid.toString().replace("-", "");
//    	String businessKey = uuidStr;
    	
    	ExecutionEntity thisEntity = (ExecutionEntity) execution;
    	//ExecutionEntity superExecEntity = thisEntity.getSuperExecution();
    	//StringUtils.isEmpty(thisEntity.getProcessBusinessKey());
//    	String pid = "";
//    	pid = execution.getVariable("processInstanceId").toString();
//    	System.out.println(pid);
//    	
//    	thisEntity.setBusinessKey(businessKey);
    	 
//		String processDefinitionId = execution.getVariable("processDefinitionId").toString();
		//修改BusinessKey
//    	ExecutionEntity executionEntity = (ExecutionEntity) execution;execution.getProcessInstanceId()
//    	ProcessInstance processInstance = executionEntity.getProcessInstance();
//        String referenceId = (String) execution.getEngineServices().getRuntimeService().getVariables(execution.getId())
//                 .get(PROCESS_REFERENCE_ID);
//    	String bid = "222222222222222";
////    	paicheService.listAll(null);
//    	System.out.println("ffffffffffffff : " + execution.getProcessInstanceId());
//        runtimeService.updateBusinessKey(execution.getProcessInstanceId(), bid);
    	
    	
//		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey, map);
//		
//		
//		record.setPid(processInstance.getId()); //获取和设置Pid
		
		
	}

}
