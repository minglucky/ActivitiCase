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

import com.zioer.dao.PaicheMapper;
import com.zioer.model.Paiche;
import com.zioer.service.PaicheService;;

@Service
public class PaicheServiceImpl implements PaicheService{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(Paiche record) {
		PaicheMapper mapper = sqlSessionTemplate.getMapper(PaicheMapper.class);
		return mapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		PaicheMapper mapper = sqlSessionTemplate.getMapper(PaicheMapper.class);
		return mapper.deleteBykey(id);

	}

	@Override
	public Paiche selectByPrimaryKey(String id) {
		PaicheMapper mapper = sqlSessionTemplate.getMapper(PaicheMapper.class);
		return mapper.findByKey(id);
		
	}

	@Override
	public List<Paiche> listAll(Paiche record) {
		PaicheMapper mapper = sqlSessionTemplate.getMapper(PaicheMapper.class);
		
		List<Paiche> list = new ArrayList<Paiche>();
		list = mapper.listAll();
		 
		return list;
	}

	@Override
	public int update(Paiche record) {
		PaicheMapper mapper = sqlSessionTemplate.getMapper(PaicheMapper.class);
		return mapper.update(record);
	}
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
}
