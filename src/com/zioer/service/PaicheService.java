package com.zioer.service;

import java.util.List;
import com.zioer.model.Paiche;

public interface PaicheService {
    int insert(Paiche record);
    int deleteByPrimaryKey(String id);
    int update(Paiche record);
    Paiche selectByPrimaryKey(String id);
    List<Paiche> listAll(Paiche record);
}
