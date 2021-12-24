package com.zioer.service.Imp;

import com.zioer.dao.ReimbursementMapper;
import com.zioer.model.Reimbursement;
import com.zioer.service.ReimbursementService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReimbursementServiceImpl implements ReimbursementService{
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insert(Reimbursement record) {
		ReimbursementMapper mapper = sqlSessionTemplate.getMapper(ReimbursementMapper.class);
		return mapper.insertReimbursement(record);
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		ReimbursementMapper mapper = sqlSessionTemplate.getMapper(ReimbursementMapper.class);
		return mapper.deleteBykey(id);

	}

	@Override
	public Reimbursement selectByPrimaryKey(String id) {
		ReimbursementMapper mapper = sqlSessionTemplate.getMapper(ReimbursementMapper.class);
		return mapper.findByKey(id);
		
	}

	@Override
	public List<Reimbursement> listAll() {
		ReimbursementMapper mapper = sqlSessionTemplate.getMapper(ReimbursementMapper.class);
		
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		list = mapper.listAll();
		 
		return list;
	}

	@Override
	public int update(Reimbursement record) {
		ReimbursementMapper mapper = sqlSessionTemplate.getMapper(ReimbursementMapper.class);
		return mapper.updateReimbursement(record);
	}

	/**
	 * 持久化内置表单数据
	 * @return
	 */
//	@Transactional
//	public Reimbursement saveReimbursement(DelegateExecution execution){
//		String userId = execution.getVariable("startUserId").toString();
//		Reimbursement reimbursement = new Reimbursement();
//
//    	reimbursement.setUserId(userId);
//    	reimbursement.setCreatedatetime(new Date());
//    	reimbursement.setPid(execution.getProcessInstanceId());
//    	reimbursement.setFee(Integer.parseInt(execution.getVariable("fee").toString()));
//    	reimbursement.setNote(execution.getVariable("note").toString());
//    	reimbursement.setType(execution.getVariable("type").toString());
//    	reimbursement.setFeedate((Date)execution.getVariable("feedate"));
//
//    	insert(reimbursement);
//		return reimbursement;
//	}
//
//	@Transactional
//	public void updateReimbursement(DelegateExecution execution){
//		Reimbursement reimbursement = new Reimbursement();
//
//		reimbursement = (Reimbursement)execution.getVariable("var");
//
//    	update(reimbursement);
//		return ;
//	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
}
