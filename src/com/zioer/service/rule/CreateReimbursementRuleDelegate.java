package com.zioer.service.rule;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.zioer.model.Reimbursement;

public class CreateReimbursementRuleDelegate implements JavaDelegate {

  public void execute(DelegateExecution execution) throws Exception {
	  DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	  
	  Reimbursement reimbursement = new Reimbursement();
	  reimbursement.setFee((Integer.parseInt( execution.getVariable("fee").toString()))); 
	  reimbursement.setType( execution.getVariable("type").toString());
	  reimbursement.setNote(execution.getVariable("note").toString());
	  try{
		  reimbursement.setFeedate(fmt.parse( execution.getVariable("feedate").toString()  ));
	  }catch(Exception e){
	  }
	  execution.setVariable("ruleInput", reimbursement);
  }
} 