package com.zioer.service.Imp;

import java.io.Serializable;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
@Component
public class ServiceTaskDemo3 implements Serializable,JavaDelegate  {
	         
	private static final long serialVersionUID = 1234L;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		System.out.println("Hello Zioer");
		System.out.println("Id : " + delegateExecution.getId());
		System.out.println("ActivityId : " + delegateExecution.getCurrentActivityId());
		
	}

}
