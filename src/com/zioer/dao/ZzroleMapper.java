package com.zioer.dao;

import java.util.List;

import com.zioer.model.Zzrole;

public interface ZzroleMapper {
	public int insertZzrole(Zzrole record);
	public int deleteBykey(String id);
	public int updateZzrole(Zzrole record);
	public List<Zzrole> listAll(Zzrole record);
	public Zzrole findByKey(String id);
}
