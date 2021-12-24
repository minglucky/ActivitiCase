package com.zioer.service.Imp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServiceTaskDemo implements  JavaDelegate {

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		System.out.println("当前节点名称 : " + delegateExecution.getCurrentActivityName());
		System.out.println("传递值 : " + delegateExecution.getVariable("user").toString());
		
	}
}
