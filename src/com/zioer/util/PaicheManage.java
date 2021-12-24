package com.zioer.util;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zioer.model.Paiche;
import com.zioer.service.PaicheService;

@Service
public class PaicheManage {
	@Autowired
    private PaicheService paicheService;
	
	/**
	 * 新增
	 * @param record
	 * @return uuid of ID
	 * null，如果没有insert
	 */
	public String save(Paiche record){
		UUID uuid = UUID.randomUUID();
    	String uuidStr = uuid.toString().replace("-", "");
    	record.setId(uuidStr);
		int ret = paicheService.insert(record);	//业务数据保存
		if (ret == 1){
			return uuidStr;
		}else{
			return null;
		}
	}
	
	/**
	 * 修改
	 * @param record
	 * @return 
	 * 1 update成功
	 * 0 update失败
	 */
	public int update(Paiche record){
		int ret = paicheService.update(record);	//业务数据保存
		return ret;
	}
	
	/**
	 * 删除单条数据
	 * @param id
	 * @return
	 */
	public int deleteOne(String id){
		int ret = paicheService.deleteByPrimaryKey(id);	//业务数据保存
		return ret;
	}
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public Paiche getOne(String id){
		Paiche ret = paicheService.selectByPrimaryKey(id);	//业务数据保存
		return ret;
	}
	
	public List<Paiche> list(Object record){
		List<Paiche> ret = paicheService.listAll( (Paiche)record);
		return ret;
	}
	

}
