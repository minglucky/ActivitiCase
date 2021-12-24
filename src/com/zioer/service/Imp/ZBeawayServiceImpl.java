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

import com.zioer.dao.ZBeawayMapper;
import com.zioer.model.ZBeaway;
import com.zioer.service.ZBeawayService;;

@Service
public class ZBeawayServiceImpl implements ZBeawayService{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(ZBeaway record) {
		ZBeawayMapper mapper = sqlSessionTemplate.getMapper(ZBeawayMapper.class);
		return mapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		ZBeawayMapper mapper = sqlSessionTemplate.getMapper(ZBeawayMapper.class);
		return mapper.deleteBykey(id);

	}

	@Override
	public ZBeaway selectByPrimaryKey(String id) {
		ZBeawayMapper mapper = sqlSessionTemplate.getMapper(ZBeawayMapper.class);
		return mapper.findByKey(id);
		
	}

	@Override
	public List<ZBeaway> listAll(ZBeaway record) {
		ZBeawayMapper mapper = sqlSessionTemplate.getMapper(ZBeawayMapper.class);
		
		List<ZBeaway> list = new ArrayList<ZBeaway>();
		list = mapper.listAll(record);
		 
		return list;
	}

	@Override
	public int update(ZBeaway record) {
		ZBeawayMapper mapper = sqlSessionTemplate.getMapper(ZBeawayMapper.class);
		return mapper.update(record);
	}
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
}
