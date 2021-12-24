package com.zioer.dao;

import java.util.List;

import com.zioer.model.Zlog;

public interface ZlogMapper {
	public int insert(Zlog record);
	public int deleteBykey(String id);
	public int update(Zlog record);
	public List<Zlog> listAll(Zlog record);
	public Zlog findByKey(String id);
}
