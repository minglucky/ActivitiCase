package com.zioer.model;

import org.activiti.engine.form.AbstractFormType;

public class DoubleFormType extends AbstractFormType{

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "double";
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValue) {
		return Double.valueOf(propertyValue);
	}

	@Override
	public String convertModelValueToFormValue(Object modelValue) {
		return modelValue != null ? modelValue.toString() : null;
	}

}
