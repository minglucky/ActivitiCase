package com.zioer.service;

import java.util.List;
import com.zioer.model.Zzdepartment;

public interface ZzdepartmentService {
    int insert(Zzdepartment record);
    int deleteByPrimaryKey(String id);
    int update(Zzdepartment record);
    Zzdepartment selectByPrimaryKey(String id);
    List<Zzdepartment> listAll(Zzdepartment record);
}
