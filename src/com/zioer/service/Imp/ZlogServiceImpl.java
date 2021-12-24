package com.zioer.service.Imp;

import org.activiti.engine.delegate.DelegateExecution;
import org.mybatis.spring.SqlSessionTemplate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zioer.dao.ZlogMapper;
import com.zioer.model.Zlog;
import com.zioer.service.ZlogService;

@Service
public class ZlogServiceImpl implements ZlogService{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(Zlog record) {
		ZlogMapper mapper = sqlSessionTemplate.getMapper(ZlogMapper.class);
		return mapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		ZlogMapper mapper = sqlSessionTemplate.getMapper(ZlogMapper.class);
		return mapper.deleteBykey(id);

	}

	@Override
	public Zlog selectByPrimaryKey(String id) {
		ZlogMapper mapper = sqlSessionTemplate.getMapper(ZlogMapper.class);
		return mapper.findByKey(id);
		
	}

	@Override
	public List<Zlog> listAll(Zlog record) {
		ZlogMapper mapper = sqlSessionTemplate.getMapper(ZlogMapper.class);
		
		List<Zlog> list = new ArrayList<Zlog>();
		list = mapper.listAll(record);
		 
		return list;
	}

	@Override
	public int update(Zlog record) {
		ZlogMapper mapper = sqlSessionTemplate.getMapper(ZlogMapper.class);
		return mapper.update(record);
	}
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
}
