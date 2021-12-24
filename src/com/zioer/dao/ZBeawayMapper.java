package com.zioer.dao;

import java.util.List;

import com.zioer.model.ZBeaway;

public interface ZBeawayMapper {
	public int insert(ZBeaway record);
	public int deleteBykey(String id);
	public int update(ZBeaway record);
	public List<ZBeaway> listAll(ZBeaway record);
	public ZBeaway findByKey(String id);
}
