package com.zioer.service.Imp;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class AssigneeGive implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		if (delegateTask.getTaskDefinitionKey().equals("usertask1")){
			//动态设置节点办理人
			//delegateTask.setVariable("user1", "lobby");
		}
	}

}
