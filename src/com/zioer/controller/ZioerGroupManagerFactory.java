package com.zioer.controller;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

public class ZioerGroupManagerFactory implements SessionFactory{
	ZioerGroupManager zioerGroupManager = new ZioerGroupManager();
	
	public void setZioerGroupManager(ZioerGroupManager zioerGroupManager) {
		this.zioerGroupManager = zioerGroupManager;
	}

	@Override
	public Class<?> getSessionType() {
		return GroupIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return zioerGroupManager;
	}

}
