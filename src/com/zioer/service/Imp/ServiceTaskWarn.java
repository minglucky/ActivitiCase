package com.zioer.service.Imp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServiceTaskWarn implements  JavaDelegate {

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		System.out.println("Hello Zioer");
		System.out.println(delegateExecution.getCurrentActivityId());
		System.out.println(delegateExecution.getCurrentActivityName());
	}
}
