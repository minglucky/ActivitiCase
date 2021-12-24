package com.zioer.dao;

import java.util.List;

import com.zioer.model.Paiche;

public interface PaicheMapper {
	public int insert(Paiche record);
	public int deleteBykey(String id);
	public int update(Paiche record);
	public List<Paiche> listAll();
	public Paiche findByKey(String id);
}
