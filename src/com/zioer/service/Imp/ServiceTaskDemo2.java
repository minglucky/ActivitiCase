package com.zioer.service.Imp;

import java.io.Serializable;

import org.activiti.engine.runtime.Execution;
import org.springframework.stereotype.Component;

@Component
public class ServiceTaskDemo2 implements Serializable {
	         
	private static final long serialVersionUID = 123L;

	public void print(Execution execution) throws Exception {
		System.out.println("Hello Zioer");
		System.out.println("Id : " + execution.getId());
		System.out.println("ActivityId : " + execution.getActivityId());
	}

}
