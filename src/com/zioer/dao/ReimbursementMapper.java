package com.zioer.dao;

import java.util.List;

import com.zioer.model.Reimbursement;

public interface ReimbursementMapper {
	public int insertReimbursement(Reimbursement record);
	public int deleteBykey(String id);
	public int updateReimbursement(Reimbursement record);
	public List<Reimbursement> listAll();
	public Reimbursement findByKey(String id);
}
