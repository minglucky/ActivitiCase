package com.zioer.service.Imp;

import org.mybatis.spring.SqlSessionTemplate;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zioer.dao.ZzuserMapper;
import com.zioer.model.Zzuser;
import com.zioer.service.ZzuserService;

@Service
public class ZzuserServiceImpl implements ZzuserService{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insert(Zzuser record) {
		ZzuserMapper mapper = sqlSessionTemplate.getMapper(ZzuserMapper.class);
		return mapper.insertZzuser(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		ZzuserMapper mapper = sqlSessionTemplate.getMapper(ZzuserMapper.class);
		return mapper.deleteBykey(id);
	}

	@Override
	public int update(Zzuser record) {
		ZzuserMapper mapper = sqlSessionTemplate.getMapper(ZzuserMapper.class);
		return mapper.updateZzuser(record);
	}

	@Override
	public Zzuser selectByPrimaryKey(String id) {
		ZzuserMapper mapper = sqlSessionTemplate.getMapper(ZzuserMapper.class);
		return mapper.findByKey(id);
	}

	@Override
	public List<Zzuser> listAll(Zzuser record) {
		ZzuserMapper mapper = sqlSessionTemplate.getMapper(ZzuserMapper.class);
		return mapper.listAll(record);
	}
	
	@Override
	public long listAllCount(Zzuser record){
		ZzuserMapper mapper = sqlSessionTemplate.getMapper(ZzuserMapper.class);
		return mapper.listAllCount(record);
	}

}
