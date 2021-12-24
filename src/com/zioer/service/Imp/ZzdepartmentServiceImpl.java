package com.zioer.service.Imp;

import org.mybatis.spring.SqlSessionTemplate;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zioer.dao.ZzdepartmentMapper;
import com.zioer.model.Zzdepartment;
import com.zioer.service.ZzdepartmentService;

@Service
public class ZzdepartmentServiceImpl implements ZzdepartmentService{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insert(Zzdepartment record) {
		ZzdepartmentMapper mapper = sqlSessionTemplate.getMapper(ZzdepartmentMapper.class);
		return mapper.insertZzdepartment(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		ZzdepartmentMapper mapper = sqlSessionTemplate.getMapper(ZzdepartmentMapper.class);
		return mapper.deleteBykey(id);
	}

	@Override
	public int update(Zzdepartment record) {
		ZzdepartmentMapper mapper = sqlSessionTemplate.getMapper(ZzdepartmentMapper.class);
		return mapper.updateZzdepartment(record);
	}

	@Override
	public Zzdepartment selectByPrimaryKey(String id) {
		ZzdepartmentMapper mapper = sqlSessionTemplate.getMapper(ZzdepartmentMapper.class);
		return mapper.findByKey(id);
	}

	@Override
	public List<Zzdepartment> listAll(Zzdepartment record) {
		ZzdepartmentMapper mapper = sqlSessionTemplate.getMapper(ZzdepartmentMapper.class);
		return mapper.listAll(record);
	}
}
