package com.zioer.service.Imp;

import org.mybatis.spring.SqlSessionTemplate;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zioer.dao.ZzroleMapper;
import com.zioer.model.Zzrole;
import com.zioer.service.ZzroleService;

@Service
public class ZzroleServiceImpl implements ZzroleService{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insert(Zzrole record) {
		ZzroleMapper mapper = sqlSessionTemplate.getMapper(ZzroleMapper.class);
		return mapper.insertZzrole(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		ZzroleMapper mapper = sqlSessionTemplate.getMapper(ZzroleMapper.class);
		return mapper.deleteBykey(id);
	}

	@Override
	public int update(Zzrole record) {
		ZzroleMapper mapper = sqlSessionTemplate.getMapper(ZzroleMapper.class);
		return mapper.updateZzrole(record);
	}

	@Override
	public Zzrole selectByPrimaryKey(String id) {
		ZzroleMapper mapper = sqlSessionTemplate.getMapper(ZzroleMapper.class);
		return mapper.findByKey(id);
	}

	@Override
	public List<Zzrole> listAll(Zzrole record) {
		ZzroleMapper mapper = sqlSessionTemplate.getMapper(ZzroleMapper.class);
		return mapper.listAll(record);
	}
}
