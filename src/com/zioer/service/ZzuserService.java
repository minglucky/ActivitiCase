package com.zioer.service;

import java.util.List;
import com.zioer.model.Zzuser;

public interface ZzuserService {
    int insert(Zzuser record);
    int deleteByPrimaryKey(String id);
    int update(Zzuser record);
    Zzuser selectByPrimaryKey(String id);
    List<Zzuser> listAll(Zzuser record);
    long listAllCount(Zzuser record);
}
