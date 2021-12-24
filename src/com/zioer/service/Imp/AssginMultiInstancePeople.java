package com.zioer.service.Imp;

import java.util.Arrays;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;

public class AssginMultiInstancePeople implements  JavaDelegate{

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		System.out.println("设置多实例参与人员");
		String[] str = StringUtils.split("lee,lobby,kitty,lucy", ",");
        
		delegateExecution.setVariable("users", Arrays.asList(str));
	}

}
