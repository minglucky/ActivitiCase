package com.zioer.dao;

import java.util.List;

import com.zioer.model.Zzdepartment;

public interface ZzdepartmentMapper {
	public int insertZzdepartment(Zzdepartment record);
	public int deleteBykey(String id);
	public int updateZzdepartment(Zzdepartment record);
	public List<Zzdepartment> listAll(Zzdepartment record);
	public Zzdepartment findByKey(String id);
}
