package com.zioer.service;

import java.util.List;
import com.zioer.model.Zzrole;

public interface ZzroleService {
    int insert(Zzrole record);
    int deleteByPrimaryKey(String id);
    int update(Zzrole record);
    Zzrole selectByPrimaryKey(String id);
    List<Zzrole> listAll(Zzrole record);
}
