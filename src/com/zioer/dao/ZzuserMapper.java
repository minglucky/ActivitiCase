package com.zioer.dao;

import java.util.List;

import com.zioer.model.Zzuser;

public interface ZzuserMapper {
	public int insertZzuser(Zzuser record);
	public int deleteBykey(String id);
	public int updateZzuser(Zzuser record);
	public List<Zzuser> listAll(Zzuser record);
	public Zzuser findByKey(String id);
	public long listAllCount(Zzuser record);
}
