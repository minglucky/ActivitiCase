package com.zioer.service.rule;

import java.util.Collection;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;


public class OutputReimbursementDelegate implements JavaDelegate {

  @SuppressWarnings("unchecked")
  public void execute(DelegateExecution execution) throws Exception {
    Collection<Object> outputList = (Collection<Object>) execution.getVariable("ruleOutput");
    for (Object object : outputList) {
    	if(object instanceof RuleOutput) {
    		execution.setVariable("bzhu", ((RuleOutput) object).getResult());    		
    	}
	}
  }
}