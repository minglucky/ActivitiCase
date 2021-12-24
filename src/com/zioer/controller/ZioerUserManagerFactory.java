package com.zioer.controller;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class ZioerUserManagerFactory implements SessionFactory{

	ZioerUserManager zioerUserManager = new ZioerUserManager();

	public void setZioerUserManager(ZioerUserManager zioerUserManager) {
		this.zioerUserManager = zioerUserManager;
	}

	@Override
	public Class<?> getSessionType() {
		return UserIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return zioerUserManager;
	}
}
