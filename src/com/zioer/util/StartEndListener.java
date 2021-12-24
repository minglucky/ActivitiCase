package com.zioer.util;

import java.util.HashMap;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;

public class StartEndListener implements  JavaDelegate {
//派车流程中开始节点用

	@Override
	public void execute(DelegateExecution execution) throws Exception {		
    	ExecutionEntity thisEntity = (ExecutionEntity) execution;
    	ExecutionEntity superExecEntity = thisEntity.getSuperExecution();

    	if (thisEntity.getProcessBusinessKey() == null && superExecEntity != null){
    		//用于该流程作为子流程时，赋值从父流程中获得的BusinessKey
    		execution.getEngineServices()
    			.getRuntimeService()
    			.updateBusinessKey(execution.getProcessInstanceId(), execution.getVariable("processBusinessKey").toString());
    		
    		thisEntity.setVariable("processBusinessKey", execution.getVariable("processBusinessKey").toString());	//同时赋值流程变量

    	}
	}

}
