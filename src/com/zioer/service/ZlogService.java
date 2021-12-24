package com.zioer.service;

import java.util.List;
import com.zioer.model.Zlog;

public interface ZlogService {
    int insert(Zlog record);
    int deleteByPrimaryKey(String id);
    int update(Zlog record);
    Zlog selectByPrimaryKey(String id);
    List<Zlog> listAll(Zlog record);
}
