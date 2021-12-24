package com.zioer.service;

import java.util.List;
import com.zioer.model.Reimbursement;

public interface ReimbursementService {
    int insert(Reimbursement record);
    int deleteByPrimaryKey(String id);
    int update(Reimbursement record);
    Reimbursement selectByPrimaryKey(String id);
    List<Reimbursement> listAll();
}
